package domain

import groovy.transform.ToString

/**
 * Created by Robert Clayforth on 01/08/2015.
 */
@ToString(ignoreNulls = true)
class Bill {

    Date generatedDate
    Date dueDate
    Date startDate
    Date endDate
    BigDecimal totalCost
    List<Subscription> subscriptions
    BigDecimal subscriptionTotalCost
    List<Call> calls
    BigDecimal callTotalCost
    List<Film> rentals
    List<Film> buyAndKeep
    BigDecimal storeTotalCost
}
