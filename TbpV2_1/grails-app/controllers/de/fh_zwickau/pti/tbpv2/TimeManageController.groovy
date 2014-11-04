package de.fh_zwickau.pti.tbpv2

class TimeManageController {


	def taskPlanningService

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	TimeManageService timeManageService

	
    def index() {
		def mymap = taskPlanningService.getTaskInfos()
		[myMapNameInGSP:mymap]
		
	}
	
	def showBookings(){
		println timeManageService.getBookingsByTask(Task.findAllById('1'))
	}

	def showBookings() {
		
	}
}
