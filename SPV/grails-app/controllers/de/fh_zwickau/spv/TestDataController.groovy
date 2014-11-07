package de.fh_zwickau.spv

class TestDataController {

    def testDataService
	
	def index() { 
		testDataService.createTestData()
		redirect controller:"exam", action:"index", method:"GET"
	}
}
