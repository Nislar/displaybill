import domain.Bill
import spock.lang.Specification

/**
 * Created by Robert Clayforth on 01/08/2015.
 */
class BillServiceHelperSpec extends Specification {

    BillServiceHelper _billServiceHelper = new BillServiceHelper()

    def 'Successfully fetch bill from web service'() {
        when: 'The web service is called'
        Bill bill = _billServiceHelper.fetchCustomersLatestBill()

        then: 'The customers bill is successfully retrieved'
    }
}
