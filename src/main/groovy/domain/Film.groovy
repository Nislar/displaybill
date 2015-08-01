package domain

import groovy.transform.ToString

/**
 * Created by Robert Clayforth on 01/08/2015.
 */
@ToString(ignoreNulls = true)
class Film {

    String title
    BigDecimal cost
}
