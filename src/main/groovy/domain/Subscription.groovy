package domain

import groovy.transform.ToString
import groovy.transform.Canonical

/**
 * Domain class that represents a subscription.
 *
 * Created by Robert Clayforth on 01/08/2015.
 */
@ToString(ignoreNulls = true)
@Canonical
class Subscription {

    String type
    String name
    String cost
}
