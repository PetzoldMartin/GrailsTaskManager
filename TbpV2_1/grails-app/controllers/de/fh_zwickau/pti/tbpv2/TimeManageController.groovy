package de.fh_zwickau.pti.tbpv2

class TimeManageController {


	def taskPlanningService

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	TimeManageService timeManageService

	
    def index() {
		def mymap = taskPlanningService.getTaskInfos()
		[myMapNameInGSP:mymap]
		
	}
	
	def showBookings(int id){
		println timeManageService.getBookingsByTask(Task.findAllById(id))
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

	
}
