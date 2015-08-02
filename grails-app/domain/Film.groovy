import groovy.transform.ToString

/**
 * Domain object that represents a Film that has been rented or purchased.
 *
 * Created by Robert Clayforth on 01/08/2015.
 */
@ToString(ignoreNulls = true)
class Film {

    String title
    BigDecimal cost
}
