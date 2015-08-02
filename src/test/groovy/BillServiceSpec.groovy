import org.apache.commons.lang.RandomStringUtils
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.test.web.client.MockRestServiceServer
import spock.lang.Specification

import java.text.SimpleDateFormat

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess

/**
 * Unit test class for {@link BillService}. Will mock out calls to web service so a test bill can be returned.
 *
 * Created by Robert Clayforth on 01/08/2015.
 */
class BillServiceSpec extends Specification {

    BillService billService = new BillService()
    MockRestServiceServer mockServer

    String generatedDate
    String dueDate
    String fromDate
    String toDate
    String totalCost
    String subscriptionName
    String subscriptionCost
    String subscriptionTotalCost
    String calledNumber
    String duration
    String callCost
    String callTotalCost
    String filmName
    String filmCost
    String storeTotalCost

    void setup() {
        mockServer = MockRestServiceServer.createServer(billService.restTemplate)
        Random random = new Random()

        generatedDate = new Date(random.nextInt()).format(BillService.DATE_FORMAT)
        dueDate = new Date(random.nextInt()).format(BillService.DATE_FORMAT)
        fromDate = new Date(random.nextInt()).format(BillService.DATE_FORMAT)
        toDate = new Date(random.nextInt()).format(BillService.DATE_FORMAT)
        totalCost = RandomStringUtils.randomNumeric(3) + '.' + RandomStringUtils.randomNumeric(2)

        subscriptionName = RandomStringUtils.randomAlphabetic(20)
        subscriptionCost = RandomStringUtils.randomNumeric(2) + '.' + RandomStringUtils.randomNumeric(2)
        subscriptionTotalCost = RandomStringUtils.randomNumeric(2) + '.' + RandomStringUtils.randomNumeric(2)

        calledNumber = RandomStringUtils.randomNumeric(10)
        duration = RandomStringUtils.randomNumeric(2) + ':' + RandomStringUtils.randomNumeric(2) + ':' + RandomStringUtils.randomNumeric(2)
        callCost = RandomStringUtils.randomNumeric(2) + '.' + RandomStringUtils.randomNumeric(2)
        callTotalCost = RandomStringUtils.randomNumeric(2) + '.' + RandomStringUtils.randomNumeric(2)

        filmName = RandomStringUtils.randomAlphabetic(15)
        filmCost = RandomStringUtils.randomNumeric(1) + '.' + RandomStringUtils.randomNumeric(2)
        storeTotalCost = RandomStringUtils.randomNumeric(2) + '.' + RandomStringUtils.randomNumeric(2)
    }

    def 'Successfully fetch the bill from web service'() {
        given: 'The customer has a bill to return'
        String returnedBill = '{\n' +
                '  "statement": {\n' +
                '    "generated": "' + generatedDate + '",\n' +
                '    "due": "' + dueDate + '",\n' +
                '    "period": {\n' +
                '      "from": "' + fromDate + '",\n' +
                '      "to": "' + toDate + '"\n' +
                '    }\n' +
                '  },\n' +
                '  "total": ' + totalCost + ',\n' +
                '  "package": {\n' +
                '    "subscriptions": [\n' +
                '      { "type": "tv", "name": "' + subscriptionName + '", "cost": ' + subscriptionCost + ' },\n' +
                '      { "type": "broadband", "name": "' + subscriptionName + '", "cost": ' + subscriptionCost + ' }\n' +
                '    ],\n' +
                '    "total": ' + subscriptionTotalCost + '\n' +
                '  },\n' +
                '  "callCharges": {\n' +
                '    "calls": [\n' +
                '      { "called": "' + calledNumber + '", "duration": "' + duration + '", "cost": ' + callCost + ' },\n' +
                '      { "called": "' + calledNumber + '", "duration": "' + duration + '", "cost": ' + callCost + ' },\n' +
                '      { "called": "' + calledNumber + '", "duration": "' + duration + '", "cost": ' + callCost + ' },\n' +
                '      { "called": "' + calledNumber + '", "duration": "' + duration + '", "cost": ' + callCost + ' }\n' +
                '    ],\n' +
                '    "total": ' + callTotalCost + '\n' +
                '  },\n' +
                '  "skyStore": {\n' +
                '    "rentals": [\n' +
                '      { "title": "' + filmName + '", "cost": ' + filmCost + ' },\n' +
                '      { "title": "' + filmName + '", "cost": ' + filmCost + ' }\n' +
                '    ],\n' +
                '    "buyAndKeep": [\n' +
                '      { "title": "' + filmName + '", "cost": ' + filmCost + ' },\n' +
                '      { "title": "' + filmName + '", "cost": ' + filmCost + ' },\n' +
                '      { "title": "' + filmName + '", "cost": ' + filmCost + ' }\n' +
                '    ],\n' +
                '    "total": ' + storeTotalCost + '\n' +
                '  }\n' +
                '}'

        when: 'The web service is called'
        mockServer.expect(requestTo(BillService.URL))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(returnedBill, MediaType.APPLICATION_JSON))

        Bill bill = billService.fetchCustomersLatestBill()

        then: 'The customers bill is successfully retrieved'
        and: 'The generated date is correct'
        bill.generated == new SimpleDateFormat(BillService.DATE_FORMAT).parse(generatedDate)

        and: 'The due date is correct'
        bill.dueDate == new SimpleDateFormat(BillService.DATE_FORMAT).parse(dueDate)

        and: 'The start date is correct'
        bill.startDate == new SimpleDateFormat(BillService.DATE_FORMAT).parse(fromDate)

        and: 'The end date is correct'
        bill.endDate == new SimpleDateFormat(BillService.DATE_FORMAT).parse(toDate)

        and: 'The total cost is correct'
        bill.totalCost == new BigDecimal(totalCost)

        and: 'The calls are correct'
        bill.calls.size() == 4
        bill.callTotalCost == new BigDecimal(callTotalCost)
        bill.calls.first().calledNumber == calledNumber
        bill.calls.first().cost == new BigDecimal(callCost)
        bill.calls.first().duration == duration

        and: 'The purchases are correct'
        bill.storeTotalCost == new BigDecimal(storeTotalCost)
        bill.buyAndKeep.size() == 3
        bill.buyAndKeep.first().title == filmName
        bill.buyAndKeep.first().cost == new BigDecimal(filmCost)

        and: 'The rentals are correct'
        bill.rentals.size() == 2
        bill.buyAndKeep.first().title == filmName
        bill.buyAndKeep.first().cost == new BigDecimal(filmCost)

        and: 'The package subscriptions are correct'
        bill.subscriptions.size() == 2
        bill.subscriptionTotalCost == new BigDecimal(subscriptionTotalCost)
        bill.subscriptions.first().cost == new BigDecimal(subscriptionCost)
        bill.subscriptions.first().name == subscriptionName
        bill.subscriptions.first().type == 'tv'
        bill.subscriptions.last().type == 'broadband'
    }
}
