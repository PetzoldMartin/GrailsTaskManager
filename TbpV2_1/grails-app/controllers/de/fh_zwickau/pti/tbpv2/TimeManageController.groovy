package de.fh_zwickau.pti.tbpv2

import java.util.Date;

import grails.transaction.Transactional;
import grails.validation.Validateable;
import groovy.json.internal.Sys;
import de.fh_zwickau.pti.tbpv2.Booking
import de.fh_zwickau.pti.tbpv2.CompoundTask
import de.fh_zwickau.pti.tbpv2.SubTask
import de.fh_zwickau.pti.tbpv2.TimePlanning
import org.grails.databinding.BindUsing;
import org.grails.databinding.BindingFormat;

class TimeManageController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	static defaultAction = "show"
	

	def taskPlanningService
	
	
	TimeManageService timeManageService

	
    def index() {
		def mymap = taskPlanningService.getTaskInfos()
		[myMapNameInGSP:mymap]
	}
	
	/**
	 * Show the contents of a Task
	 * @param id Task ID
	 * @return internal content
	 */
	def show(int id) {
		def mymap = timeManageService.getTask(id)
		[map:mymap]
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
	def updateBookings(){
		int amount = 0
		if(params.amount.grep(~/\d+/)[0]!=null)
		{amount = params.amount.grep(~/\d+/)[0].toInteger()}
		int[] arr = Task.findAllById(params.int("taskid"))[0].bookings.amount;
		int amountSum=0
		for (i in arr) {
			amountSum+=i
		}
		def bookingCMD = 
		new CreateBookingCmd( amount: amount
					,start: Date.parse('dd.MM.yyyy',params.start.grep(~/\d\d\.\d\d\.\d\d\d\d/)[0])
					,end: Date.parse('dd.MM.yyyy',params.end.grep(~/\d\d\.\d\d\.\d\d\d\d/)[0])
					,taskid:params.int("taskid")
					,plannedHours: Task.findAllById(params.int("taskid"))[0].plans.timeBudgetPlan[0]
					,bookedHours: amountSum + amount
					)
		timeManageService.updateBookings(bookingCMD)
		
		println bookingCMD
		println Task.findAllById(bookingCMD.taskid)[0].plans.timeBudgetPlan[0]
		println amountSum
		forward action: "showBookings" ,id: params.getAt("taskid")		
	}
	
	@Transactional
	def deleteBookings() {
		timeManageService.deleteBooking(1);
		forward action: "showBookings" ,id: params.getAt("taskid")
	}
	
	@Validateable
	class CreateBookingCmd {
		int taskid
		int plannedHours
		int bookedHours
		int amount 
		Date start
		Date end	
	
	static constraints = {
		amount min:1
		start (validator:{val, obj ->obj.end >= val})
		end max: new Date()
		bookedHours (validator:{val, obj ->obj.plannedHours >= val})
	}

	def String toString() {
		"taskid: ${taskid},amount: ${amount},start: ${start},end: ${end},plannedHours: ${plannedHours},bookedHours: ${bookedHours}"
	};
	}
	
}