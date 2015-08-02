import displaybill.DisplayBillController
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * Unit test class for {@link DisplayBillController}.
 */
@TestFor(DisplayBillController)
class DisplayBillControllerSpec extends Specification {

    BillService billServiceMock = Mock(BillService)

    def setup() {
        controller.billService = billServiceMock
    }

    void "test that the bill service is called"() {
        when: 'The controller method is called'
        controller.index()

        then: 'The bill service method is called to retrieve the customers bill'
        1 * billServiceMock.fetchCustomersLatestBill() >> { return new Bill() }
    }
}
