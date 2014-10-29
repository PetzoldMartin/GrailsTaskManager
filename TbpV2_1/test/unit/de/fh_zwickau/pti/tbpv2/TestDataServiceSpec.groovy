package de.fh_zwickau.pti.tbpv2

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(TestDataService)
@Mock([SubTask, CompoundTask, Task, Booking])
class TestDataServiceSpec extends Specification {

	def testdata 
	
	def setup() {
		testdata = service.generateTestData()
	}

	void "test creation of test data"() {
		expect: "a couple of test data sets should be created"
		5 == Task.count
	}
	
}
