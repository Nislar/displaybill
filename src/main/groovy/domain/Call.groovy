package domain

import groovy.transform.ToString
import groovy.transform.Canonical

/**
 * Domain object that represents a telephone call that was made.
 *
 * Created by Robert Clayforth on 01/08/2015.
 */
@ToString(ignoreNulls = true)
@Canonical
class Call {

    String calledNumber
    String duration
    String cost
}
