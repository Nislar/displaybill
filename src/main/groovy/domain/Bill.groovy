package domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import groovy.transform.ToString
import groovy.transform.Canonical

/**
 * Domain object representing a customers bill for a set date range.
 *
 * Created by Robert Clayforth on 01/08/2015.
 */
@ToString
@Canonical
@JsonIgnoreProperties(ignoreUnknown = true)
class Bill {

    String generated
    String dueDate
    String startDate
    String endDate
    String totalCost
    List<Subscription> subscriptions
    String subscriptionTotalCost
    List<Call> calls
    String callTotalCost
    List<Film> rentals
    List<Film> buyAndKeep
    String storeTotalCost
}
