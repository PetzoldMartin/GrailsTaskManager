package de.fh_zwickau.pti.tbpv2

import grails.transaction.Transactional;
import groovy.json.internal.Sys;
import de.fh_zwickau.pti.tbpv2.Booking
import de.fh_zwickau.pti.tbpv2.CompoundTask
import de.fh_zwickau.pti.tbpv2.SubTask
import de.fh_zwickau.pti.tbpv2.TimePlanning

class TimeManageController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	

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
	def updateBookings(){
		
		def booking =
		new Booking( amount: params.amount.grep(~/\d+/)
					, start: Date.parse('dd.MM.yyyy',params.start.grep(~/\d\d\.\d\d\.\d\d\d\d/)[0])
					,end: Date.parse('dd.MM.yyyy',params.end.grep(~/\d\d\.\d\d\.\d\d\d\d/)[0])  )
		
		timeManageService.updateBookings(booking,params.int("taskid"))
		
		forward action: "showBookings" ,id: params.getAt("taskid")
		
		
	}

	
}
