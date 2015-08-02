package displaybill

import geb.spock.GebSpec
import grails.test.mixin.integration.Integration
import grails.transaction.Rollback

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@Integration
@Rollback
class DisplayBillFunctionalTestSpec extends GebSpec {

    def setup() {
    }

    def cleanup() {
    }

    def 'Scenario 1: View their overall statement'() {
        given: 'They are an existing customer'

        when: 'They view their statement'
        go '/displayBill/index'

        then: 'The statement period from and to date are displayed'
        $('span.statementHeading').text() == 'Statement covering Jan 26, 2015 to Feb 25, 2015'

        and: 'The statement due date is displayed'
        and: 'The statement total cost is displayed'
        $('h4.totalCostDetail').text() == 'GBP136.03 due on Jan 25, 2015'

        and: 'The call charge total cost is displayed'
        $('h4.callsHeading').text() == '28 calls for GBP59.64'

        and: 'The package total cost is displayed'
        $('h4.packageHeading').text().startsWith('3 package subscriptions (')
        $('h4.packageHeading').text().endsWith(') for GBP71.40')

        and: 'The sky store purchase total cost is displayed'
        $('h4.storeHeading').text() == '1 rentals and 2 buy and keep purchases for GBP24.97'
    }

//    def 'Scenario 2: View their current package details'() {
//        given: 'They are an existing customer'
//
//        when: 'They view their statement'
//        go '/displayBill/index'
//
//        and: 'They want to see their current package subscriptions'
//        $('a.subscriptionViewToggle').click()
//
//        then: 'The type, name and cost is displayed for all package subscriptions'
        // todo - the check below refuses to work even though it works when entered into the browser even if the panel is closed
        // If the panel is started open it works, but not when the panel is started closed.
//        waitFor(10) {
//            $('li.tv h5 span.subscriptionDetail').text() == 'Variety with Movies HD for GBP50.00'
//        }
//        $('li.talk h5 span.subscriptionDetail').text() == 'Sky Talk Anytime for GBP5.00'
//        $('li.broadband h5 span.subscriptionDetail').text() == 'Fibre Unlimited for GBP16.40'
//    }

//    def 'Scenario 3: View their call charges in the statement period'() {
//        Given: 'They are an existing customer'
//
//        When: 'They view their statement'
//
//        And: 'They want to see all their call charges'
//
//        Then: 'All the calls made during the statement period are displayed'
//
//        And: 'The call number, the duration and the cost are displayed for all calls'
//
//    }
//
//    def 'Scenario 4: View all of their store purchases made in the statement period'() {
//        Given: 'They are an existing customer'
//
//        When: 'They view their statement'
//
//        And: 'They want to see all their store purchases'
//
//        Then: 'All the rentals made during the statement period are displayed'
//
//        And: 'The rental name and cost is displayed for each rental'
//
//        And: 'All of the buy and keep purchases made during the statement period are displayed'
//
//        And: 'The name and cost is displayed for each but and keep purchase'
//
//    }
}
