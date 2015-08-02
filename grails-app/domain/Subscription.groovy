import groovy.transform.ToString

/**
 * Domain class that represents a subscription.
 *
 * Created by Robert Clayforth on 01/08/2015.
 */
@ToString(ignoreNulls = true)
class Subscription {

    String type
    String name
    BigDecimal cost
}
