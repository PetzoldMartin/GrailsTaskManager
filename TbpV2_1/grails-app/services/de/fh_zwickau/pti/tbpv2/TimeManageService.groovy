package de.fh_zwickau.pti.tbpv2

import grails.transaction.Transactional

@Transactional
class TimeManageService {

	TaskService taskService
	
	
    def getTasks() {
		def tasks=Task.getAll()
		[tasks: tasks]
    }
	
	def getBookingsByTask(Task task){
		def bookings=Booking.findAllByTask(task);
		[bookings: bookings]
	}
}
