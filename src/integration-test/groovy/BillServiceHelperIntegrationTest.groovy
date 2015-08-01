import domain.Bill

/**
 * Integration test for {@link BillServiceHelper}. This will call the actual web service and test that the results are
 * returned successfully.
 *
 * Created by Robert Clayforth on 01/08/2015.
 */
class BillServiceHelperIntegrationTest {

    BillServiceHelper billServiceHelper = new BillServiceHelper()

    def 'Successfully call the bill web service and retrieve the correct bill'() {
        when: 'The web service is called'
        Bill bill = billServiceHelper.fetchCustomersLatestBill()

        then: 'The returned bill is correct'
        bill.generatedDate == '2015-01-11'
        bill.totalCost == '136.03'
        bill.subscriptions.size() == 3
        bill.calls.size() == 28
        bill.rentals.size() == 1
        bill.buyAndKeep.size() == 2
    }
}
