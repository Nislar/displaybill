import groovy.transform.ToString

import java.text.DecimalFormat

/**
 * Domain object representing a customers bill for a set date range.
 *
 * Created by Robert Clayforth on 01/08/2015.
 */
@ToString
class Bill {

    Date generated
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

    static hasMany = [
            subscriptions: Subscription,
            calls: Call,
            rentals: Film,
            buyAndKeep: Film
    ]
}
