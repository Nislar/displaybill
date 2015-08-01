import domain.Bill
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.test.web.client.MockRestServiceServer
import spock.lang.Specification
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess

/**
 * Unit test class for {@link BillServiceHelper}. Will mock out calls to web service so a test bill can be returned.
 *
 * Created by Robert Clayforth on 01/08/2015.
 */
class BillServiceHelperSpec extends Specification {

    BillServiceHelper billServiceHelper = new BillServiceHelper()

    def 'Successfully fetch bill from web service'() {
        given: ''
        String returnedBill = '{\n' +
                '  "statement": {\n' +
                '    "generated": "2015-08-01",\n' +
                '    "due": "2015-01-25",\n' +
                '    "period": {\n' +
                '      "from": "2015-01-26",\n' +
                '      "to": "2015-02-25"\n' +
                '    }\n' +
                '  },\n' +
                '  "total": 136.03,\n' +
                '  "package": {\n' +
                '    "subscriptions": [\n' +
                '      { "type": "tv", "name": "Variety with Movies HD", "cost": 50.00 },\n' +
                '      { "type": "broadband", "name": "Fibre Unlimited", "cost": 16.40 }\n' +
                '    ],\n' +
                '    "total": 71.40\n' +
                '  },\n' +
                '  "callCharges": {\n' +
                '    "calls": [\n' +
                '      { "called": "07716393769", "duration": "00:23:03", "cost": 2.13 },\n' +
                '      { "called": "07716393769", "duration": "00:23:03", "cost": 2.13 },\n' +
                '      { "called": "07716393769", "duration": "00:23:03", "cost": 2.13 },\n' +
                '      { "called": "02074351359", "duration": "00:23:03", "cost": 2.13 }\n' +
                '    ],\n' +
                '    "total": 59.64\n' +
                '  },\n' +
                '  "skyStore": {\n' +
                '    "rentals": [\n' +
                '      { "title": "Film 4", "cost": 4.99 },\n' +
                '      { "title": "Film 5", "cost": 4.99 }\n' +
                '    ],\n' +
                '    "buyAndKeep": [\n' +
                '      { "title": "Film 1", "cost": 9.99 },\n' +
                '      { "title": "Film 2", "cost": 9.99 },\n' +
                '      { "title": "Film 3", "cost": 9.99 }\n' +
                '    ],\n' +
                '    "total": 24.97\n' +
                '  }\n' +
                '}'

        when: 'The web service is called'
        MockRestServiceServer mockServer = MockRestServiceServer.createServer(billServiceHelper.restTemplate)
        mockServer.expect(requestTo(BillServiceHelper.URL))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(returnedBill, MediaType.APPLICATION_JSON))

        Bill bill = billServiceHelper.fetchCustomersLatestBill()

        println bill

        then: 'The customers bill is successfully retrieved'
        bill.generated == '2015-08-01'
        bill.calls.size() == 4
        bill.buyAndKeep.size() == 3
        bill.rentals.size() == 2
        bill.subscriptions.size() == 2
    }
}
