package de.fh_zwickau.spv

import grails.test.mixin.*
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(TestDataService)
@Mock([Exam, ExamResult, Lecturer, Student, Subject])
class TestDataServiceSpec extends Specification {

    void "createTestData"() {
		when: "test data were created"
		
		service.createTestData()
		
		then: "lots of objects are in the database"
		
		Person.count() == 8
		Subject.count() == 4
		Exam.count() == 8
		ExamResult.count() == 16
    }
}
