package displaybill

/**
 * Controller that will allow a bill to be displayed on a web page.
 */
class DisplayBillController {
    def billService

    def index() {
        def bill = billService.fetchCustomersLatestBill()
        [bill:bill]
    }
}
