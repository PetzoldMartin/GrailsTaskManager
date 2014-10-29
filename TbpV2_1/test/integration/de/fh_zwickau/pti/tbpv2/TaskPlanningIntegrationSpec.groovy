package de.fh_zwickau.pti.tbpv2



import java.security.Provider.Service;

import spock.lang.*

/**
 *
 */
class TaskPlanningIntegrationSpec extends Specification {

	TaskPlanningService taskPlanningService
	TestDataService testDataService

	def setup() {
	}

	def cleanup() {
	}

	void "a list of taskinfo maps can be made available"() {
		when: "test data are generated"
		testDataService.generateTestData()

		then: "test data are available"
		Task.count == 5

		and:
		when: "task info list is built from data"
		def taskinfos = taskPlanningService.taskInfos.taskInfos

		then: "task infos are built"
		taskinfos
		taskinfos.size == 5
	}

	void "taskinfo map contains the right values"() {
		given: "two tasks with plannings and bookings"
		def task1 = new CompoundTask(name: "SuperTask1", description: "compound1")
		def task2 = new SubTask(name: "SubTask1", description: "sub1")
		def tp1 = new TimePlanning(tstamp: new Date().next().clearTime(), description: "Start nächste Woche!", timeBudgetPlan: 10, start: new Date().clearTime()+7)
		def tp2 = new TimePlanning(tstamp: new Date(), description: "Start morgen!", timeBudgetPlan: 20, start: new Date().clearTime().next())
		def b1 = new Booking(date: new Date(), amount: 3, start: new Date().clearTime()-3, end: new Date().clearTime()-2)
		def b2 = new Booking(date: new Date(), amount: 5, start: new Date().clearTime()-2, end: new Date().clearTime()-1)
		task1.addToPlans tp1 
		task2.addToPlans tp2
		task2.addToBookings b1
		task2.addToBookings b2
		task1.addToSubtasks task2
		task1.save flush: true
		task2.save flush: true
		
		when: "task info list is built from data"
		def taskinfos = taskPlanningService.taskInfos.taskInfos
		def compoundtasks = taskPlanningService.taskInfos.compoundTasks
		
		def ti1 = taskinfos[0]
		def ti2 = taskinfos[1]
		
		then: "taskinfo holds the right values and one compound task is found"
		
		compoundtasks.size() == 1
		
		ti1.name == "SuperTask1"
		ti1.description == "compound1"
		ti1.timeBudgetPlan == 10
		ti1.timeBudgetUsed == 8
		ti1.start == new Date().clearTime() + 7
		ti1.compound
		
		!ti2.compound

	}
}
