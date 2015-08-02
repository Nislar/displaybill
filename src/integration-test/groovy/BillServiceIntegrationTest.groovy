import spock.lang.Specification

import java.text.SimpleDateFormat

/**
 * Integration test for {@link BillService}. This will call the actual web service and test that the results are
 * returned successfully.
 *
 * Created by Robert Clayforth on 01/08/2015.
 */
class BillServiceIntegrationTest extends Specification {

    BillService billHelper = new BillService()

    def 'Successfully call the bill web service and retrieve the correct bill'() {
        when: 'The web service is called'
        Bill bill = billHelper.fetchCustomersLatestBill()

        then: 'The returned bill is correct'
        bill.generated == new SimpleDateFormat(BillService.DATE_FORMAT).parse('2015-01-11')
        bill.totalCost == new BigDecimal('136.03')
        bill.subscriptions.size() == 3
        bill.calls.size() == 28
        bill.rentals.size() == 1
        bill.buyAndKeep.size() == 2
    }
}
