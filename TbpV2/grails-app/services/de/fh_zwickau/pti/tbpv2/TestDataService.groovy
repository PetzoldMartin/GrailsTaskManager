package de.fh_zwickau.pti.tbpv2

import grails.transaction.Transactional

@Transactional
class TestDataService {

	def generateTestData() {
		if(!CompoundTask.count()) {
			def prj = new CompoundTask(name: "exproject", description: "Generierte Beispieldaten: Top Level Projekt-Task")
			def ctask = new CompoundTask(name: "extaskgroup1", description: "Generierte Beispieldaten: Arbeitspaket 1")
			def stask1 = new SubTask(name: "exsubtask1", description: "Teilaufgabe 1")
			def stask2 = new SubTask(name: "exsubtask2", description: "Teilaufgabe 2")
			def stask3 = new SubTask(name: "exsubtask3", description: "Teilaufgabe 3")
			def b11 = new Booking(date: new Date(), amount: 3, start: new Date().clearTime()-3, end: new Date().clearTime()-2)
			def b12 = new Booking(date: new Date(), amount: 5, start: new Date().clearTime()-2, end: new Date().clearTime()-1)
			def b13 = new Booking(date: new Date(), amount: 8, start: new Date().clearTime()-1, end: new Date().clearTime())
			def b21 = new Booking(date: new Date(), amount: 12, start: new Date().clearTime()-3, end: new Date().clearTime()-2)
			def b31 = new Booking(date: new Date(), amount: 2, start: new Date().clearTime()-2, end: new Date().clearTime()-1)
			def b32 = new Booking(date: new Date(), amount: 10, start: new Date().clearTime()-1, end: new Date().clearTime())

			stask1.addToBookings b11
			stask1.addToBookings b12
			stask1.addToBookings b13
			stask2.addToBookings b21
			stask3.addToBookings b31
			stask3.addToBookings b32

			prj.addToSubtasks ctask
			prj.addToSubtasks stask3
			ctask.addToSubtasks stask1
			ctask.addToSubtasks stask2
			prj.save(flush: true)
			log.info "\ncreating project $prj"
		}
	}
	
	def deleteTestData() {
		Task task = CompoundTask.findByName "exproject"
		log.info "\ndeleting task $task"
		task.delete(flush: true)
	}
}
