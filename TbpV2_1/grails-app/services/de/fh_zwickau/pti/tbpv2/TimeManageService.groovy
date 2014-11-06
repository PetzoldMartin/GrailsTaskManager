package de.fh_zwickau.pti.tbpv2

import de.fh_zwickau.pti.tbpv2.TimeManageController.CreateBookingCmd
import grails.transaction.Transactional
import grails.validation.Validateable;

@Transactional
class TimeManageService {

	TaskService taskService
	
	
    def getTask(int id) {
		
		if (id == 0)
		 id = 1
		
		def task = Task.findAllById(id)
		
		[root: task , leafs: task.subtasks]
    }
	
	def getBookingsByTask(Task task){
			def bookings=Booking.findAllByTask(task);
			def taskid=task.id
			[bookings: bookings,taskid: taskid]
		
		
	}
	def updateBookings(CreateBookingCmd createBookingcmd) {
		if(createBookingcmd.validate()){
		def booking = new Booking(amount: createBookingcmd.amount,
			start: createBookingcmd.start,
			end: createBookingcmd.end
			
			)
		Task.findAllById(createBookingcmd.taskid)[0].addToBookings booking
		
		booking.save flush: booking.validate()}
		
		
	
		
		
		
	}
	
	
}
