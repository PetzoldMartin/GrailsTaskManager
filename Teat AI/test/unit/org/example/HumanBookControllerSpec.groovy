package org.example

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(HumanBookController)
class HumanBookControllerSpec extends Specification {

	static scaffold = Book
	
    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
    }
}
