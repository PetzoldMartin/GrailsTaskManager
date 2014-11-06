package de.fh_zwickau.pti.tbpv2

import grails.transaction.Transactional
import grails.validation.Validateable;

@Transactional
class TimeManageService {

	TaskService taskService
	
	
    def getTasks() {
		def tasks=Task.getAll()
		[tasks: tasks]
    }
	
	def getBookingsByTask(Task task){
		if (task instanceof SubTask){
			def bookings=Booking.findAllByTask(task);
			def taskid=task.id
			[bookings: bookings,taskid: taskid]
		}else{
			def bookings=null
			def taskid=task.id
			[bookings: bookings,taskid: taskid]

		}
		
	}
	def updateBookings(Booking booking,int taskid) {
		
		Task.findAllById(taskid)[0].addToBookings booking
		booking.save flush: true
		
	
		
		println booking.toString()
		
	}
	
	
}
