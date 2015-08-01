import domain.Bill
import domain.Call
import domain.Film
import domain.Subscription
import groovy.json.JsonSlurper
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate

/**
 * Class that handles all web service calls related to bills.
 *
 * Created by Robert Clayforth on 01/08/2015.
 */
class BillServiceHelper {

    static final String URL = 'http://safe-plains-5453.herokuapp.com/bill.json'
    RestTemplate restTemplate = new RestTemplate()

    /**
     * Will call a web service that will return a customers bill.
     *
     * @return a {@link Bill} object that contains a customers bill.
     */
    Bill fetchCustomersLatestBill() {
        Map<String, String> headers = ['Accept': MediaType.APPLICATION_JSON_VALUE]
        HttpEntity entity = new HttpEntity<String>("", new HttpHeaders(all: headers))
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, entity, String)

        println 'Response = ' + responseEntity

        def responseBody = new JsonSlurper().parseText(responseEntity.body)

        println 'Body = ' + responseBody

        new Bill(callTotalCost: responseBody.callCharges.total,
                dueDate: responseBody.statement.due,
                endDate: responseBody.statement.period.to,
                generated: responseBody.statement.generated,
                startDate: responseBody.statement.period.from,
                storeTotalCost: responseBody.skyStore.total,
                subscriptionTotalCost: responseBody.package.total,
                totalCost: responseBody.total,
                calls: buildCallList(responseBody),
                subscriptions: buildSubscriptionList(responseBody),
                rentals: buildRentalList(responseBody),
                buyAndKeep: buildPurchases(responseBody))
    }

    /**
     * Will build up a list of calls for the passed in web service response body.
     *
     * @param responseBody - the response body returned from the web service call.
     * @return a {@link List} of {@link Call calls}  that contain all of the calls the customer has made within the
     * bill period.
     */
    private static List<Call> buildCallList(responseBody) {
        List<Call> calls = []
        responseBody.callCharges.calls.each {
            Call call = new Call(calledNumber: it.called,
                    cost: it.cost,
                    duration: it.duration)
            calls << call
        }
        calls
    }

    /**
     * Will build up a list of the customers package subscriptions for the passed in web service response body.
     *
     * @param responseBody - the response body returned from the web service call.
     * @return a {@link List} of {@link Subscription subscription} that contain all of the customers package subscription
     * for the bill period.
     */
    private static List<Subscription> buildSubscriptionList(responseBody) {
        List<Subscription> subscriptions = []
        responseBody.package.subscriptions.each {
            Subscription subscription = new Subscription(type: it.type,
                    name: it.name,
                    cost: it.cost)
            subscriptions << subscription
        }
        subscriptions
    }

    /**
     * Will build up a list of the films the customer has rented for the passed in web service response body.
     *
     * @param responseBody - the response body returned from the web service call.
     * @return a {@link List} of {@link Film films} that contain all of the customers rentals for the bill period.
     */
    private static List<Film> buildRentalList(responseBody) {
        List<Film> rentals = []
        responseBody.skyStore.rentals.each {
            Film film = new Film(title: it.title,
                    cost: it.cost)
            rentals << film
        }
        rentals
    }

    /**
     * Will build up a list of the films the customer has purchased for the passed in web service response body.
     *
     * @param responseBody - the response body returned from the web service call.
     * @return a {@link List} of {@link Film films} that contain all of the customers purchases for the bill period.
     */
    private static List<Film> buildPurchases(responseBody) {
        List<Film> purchases = []
        responseBody.skyStore.buyAndKeep.each {
            Film film = new Film(title: it.title,
                    cost: it.cost)
            purchases << film
        }
        purchases
    }
}
