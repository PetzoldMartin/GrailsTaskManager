package de.fh_zwickau.pti.tbpv2

import de.fh_zwickau.pti.tbpv2.TimeManageController.BookingCmd
import grails.transaction.Transactional
import grails.validation.Validateable;

@Transactional
class TimeManageService {

	TaskService taskService


	def getTask(int id) {

		if (id == 0) {
			def rootProjects = []
			for (pro in Task.findAll()) {
				if (pro.superTask == null)
					rootProjects << getInfo(pro)
			}
			[root: null , leafs: rootProjects]
		} else {
			def task = Task.findAllById(id)
			[root: getInfo(task) , leafs: getLeafs(task)]
		}
	}

	def getLeafs(Task task) {
		def sub = []
		if (task instanceof CompoundTask) {
			for (leaf in task.subtasks) {
				sub << getInfo(leaf)
			}
		}
		sub
	}


	def getInfo(Task task) {
		boolean comp = task instanceof CompoundTask
		[name: task.name, id: task.id, description: task.description, compound: comp, parent: task.superTask]
	}

	def getBookingsByTask(Task task) {
		def bookings=Booking.findAllByTask(task);
		def taskid=task.id
		def timeBudgetPlan=Task.findAllById(task.id)[0].getTimeBudgetPlaned()
		def bookedTime=Task.findAllById(task.id)[0].getTimeBudgetUsed()
		[bookings: bookings,taskid: taskid,timeBudgetPlan: timeBudgetPlan,bookedTime: bookedTime]
	}
	def updateBookings(BookingCmd CMD) {
		//println CMD.inspect()

		if(CMD.validate()){
			if(CMD.isNew){
				newBooking(CMD)
		}else{
			if(CMD.toDelete){
				deleteBooking(CMD)
		}else{
		changeBooking(CMD)
		}}
		}
	}
		
	def newBooking(BookingCmd CMD) {
		def booking = new Booking(amount: CMD.amount,
		start: CMD.start,
		end: CMD.end

		)
		Task.findAllById(CMD.taskid)[0].addToBookings booking

		booking.save flush: booking.validate()
	}
	
	def deleteBooking(BookingCmd CMD){
		def booking=Booking.findAllById(CMD.bid)[0]
		println "\ndeleteBocking " + booking.inspect()
		if (booking == null) {
			notFound()
			return
		}
		Task.findAllById(CMD.taskid)[0].removeFromBookings booking
		booking.delete flush:true
	}
	
	def changeBooking(BookingCmd CMD){
		def booking=Booking.findAllById(CMD.bid)[0]
		
		//println "\nchange " + booking.inspect()
		//println "\nchange " + CMD.inspect()
		
		if(booking.amount!=CMD.amount|
			booking.start!=CMD.start|
			booking.end!=CMD.end){
			booking.amount=CMD.amount
			booking.start=CMD.start
			booking.end=CMD.end
			booking.save flush:true
			//println "changes"
		}
		
	}

	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'Booking.label', default: 'booking'), params.id])
				redirect action: "index", method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}
}



