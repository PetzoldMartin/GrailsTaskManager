package de.fh_zwickau.pti.tbpv2

import java.util.Date;

import grails.plugin.springsecurity.annotation.Secured;
import grails.transaction.Transactional;
import grails.validation.Validateable;
import groovy.json.internal.Sys;
import de.fh_zwickau.pti.tbpv2.Booking
import de.fh_zwickau.pti.tbpv2.CompoundTask
import de.fh_zwickau.pti.tbpv2.SubTask
import de.fh_zwickau.pti.tbpv2.TimePlanning

import org.grails.databinding.BindUsing;
import org.grails.databinding.BindingFormat;
@Secured(['ROLE_ADMIN','ROLE_USER','ROLE_VIEW'])
class TimeManageController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	static defaultAction = "show"
	
	def taskPlanningService
	def timeManageService

	
    //def index() {
	//	def mymap = taskPlanningService.getTaskInfos()
	//	[myMapNameInGSP:mymap]
	//}
	
	/**
	 * Show the contents of a Task
	 * @param id Task ID
	 * @return internal content
	 */
	def show(int id) {
		def mymap = timeManageService.getTask(id)
		[map:mymap]
	}
	
	/**
	 * Calls show with the id from parent
	 * @param id TaskId
	 * @return Parent Map with subtasks
	 */
	def showParent(int id) {
		redirect(action: "show", id: Task.findById(id).superTask.id)
	}
	
	def showBookings(int id){
		Task task=Task.findAllById(id)[0]
		if(task instanceof SubTask){
		timeManageService.getBookingsByTask(task)}
		else{
			forward action: "show"
		}
	}
	@Transactional
	@Secured(['ROLE_ADMIN','ROLE_USER'])
	def updateBookings(){
//						println params
//		println params.id.length
						withForm {
		for(int i=0;i<params.id.length-1; i++){
//			println i
			
			def CMD=new BookingCmd(
				bid: params.id[i].toInteger(),
				taskid: params.int("taskid"),
				plannedHours: Task.findById(params.int("taskid")).getTimeBudgetPlaned(),
				bookedHours: Task.findById(params.int("taskid")).getTimeBudgetUsed(),
				amount: params.amount[i].toInteger(),
				isNew: params.isNew[i].toBoolean(),
				start: Date.parse('dd.MM.yyyy',params.start.grep(~/\d\d\.\d\d\.\d\d\d\d/)[i]),
				end: Date.parse('dd.MM.yyyy',params.end.grep(~/\d\d\.\d\d\.\d\d\d\d/)[i])
				)

			if(params.id[i] in params.toDelete){CMD.toDelete=true}
			
			if(Booking.findAllById(CMD.bid)[0]!=null){CMD.bookedHours=CMD.bookedHours-Booking.findAllById(CMD.bid)[0].amount}
			
			timeManageService.updateBookings(CMD)
			
			
		}
		
		forward action: "showBookings" ,id: params.getAt("taskid")}
//						.invalidToken {
//						render "invalid form submission"
//						
//						}
		
	}
	
	
	@Validateable
	class BookingCmd {
		int taskid
		int plannedHours
		int bookedHours
		int amount 
		Date start
		Date end
		boolean toDelete
		boolean isNew
		int bid	
	
	static constraints = {
		amount min:1
		start (validator:{val, obj ->obj.end >= val})
		end max: new Date()
		bookedHours (validator:{val, obj ->obj.plannedHours >= val+obj.amount | obj.toDelete})
		bid nullable:true
	}

	
	def String toString() {
		"toDelete: ${toDelete},isNew: ${isNew},taskid: ${taskid},bid: ${bid},amount: ${amount},start: ${start},end: ${end},plannedHours: ${plannedHours},bookedHours: ${bookedHours}"
	};

	}
	
	
	

	
}
