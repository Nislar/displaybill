import groovy.transform.ToString

/**
 * Domain object that represents a telephone call that was made.
 *
 * Created by Robert Clayforth on 01/08/2015.
 */
@ToString(ignoreNulls = true)
class Call {

    String calledNumber
    String duration
    BigDecimal cost
}
