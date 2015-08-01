package domain

import groovy.transform.ToString
import groovy.transform.Canonical

/**
 * Domain object that represents a Film that has been rented or purchased.
 *
 * Created by Robert Clayforth on 01/08/2015.
 */
@ToString(ignoreNulls = true)
@Canonical
class Film {

    String title
    String cost
}
