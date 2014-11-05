package de.fh_zwickau.pti.tbpv2

import grails.transaction.Transactional;

class TimeManageController {


	def taskPlanningService

	
	TimeManageService timeManageService

	
    def index() {
		def mymap = taskPlanningService.getTaskInfos()
		[myMapNameInGSP:mymap]
		
	}
	
	def showBookings(int id){
		timeManageService.getBookingsByTask(Task.findAllById(id))
	}
	
	/**
	 * Show the contents of a Task
	 * @param id Task ID
	 * @return internal content
	 */
	def show(int id) {
		def mymap = taskPlanningService.getTaskInfos()
		[myMapNameInGSP:mymap]
	}
	
	@Transactional
	def updateBookings(int test){
		timeManageService.updateBookings(test)
		println test
		println "testerfolg"
		
	}

	
}
