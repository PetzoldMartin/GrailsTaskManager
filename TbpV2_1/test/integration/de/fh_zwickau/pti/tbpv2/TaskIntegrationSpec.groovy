package de.fh_zwickau.pti.tbpv2
import spock.lang.*
/**
 *
 */
class TaskIntegrationSpec extends Specification {

	void "adding TimePlannings to a Task subclass"() {
		given: "a new compound class with three time plannings in time order t1, t3, t2"
		def task = new CompoundTask(name: "SuperTask1", description: "st1")
		def tp1 = new TimePlanning(tstamp: new Date().next(),
		description: "Start nächste Woche!",
		timeBudgetPlan: 10, start: new Date().clearTime()+7)
		def start = new Date().clearTime().next()
		def tp2 = new TimePlanning(tstamp: new Date() + 5,
		description: "Start morgen!",
		timeBudgetPlan: 20, start: start)
		def tp3 = new TimePlanning(tstamp: new Date() + 3,
		description: "Start sofort!",
		timeBudgetPlan: 30, start: new Date().clearTime())
		task.addToPlans(tp1)
		task.addToPlans(tp2)
		task.addToPlans(tp3)

		when: "task ist saved"
		task.save(flush: true)
		def taskr = Task.get task.id

		then: "Task is saved, TimePlannings are saved in time order and the latest plan is retrieved from task"
		tp1 == taskr.plans[0]
		tp3 == taskr.plans[1]
		tp2 == taskr.plans[2]
		3 == TimePlanning.count
		20 == task.timeBudgetPlan
		20 == taskr.timeBudgetPlan
		start == task.start
		start == taskr.start
	}

	void "removing a Task with all its TimePlannings"() {
		given: "a new compound class with two time plannings"
		def task = new CompoundTask(name: "SuperTask1", description: "st1")
		def tp1 = new TimePlanning(tstamp: new Date().next(), description: "Start nächste Woche!", timeBudgetPlan: 10, start: new Date().clearTime()+7)
		def tp2 = new TimePlanning(tstamp: new Date(), description: "Start morgen!", timeBudgetPlan: 20, start: new Date().clearTime().next())
		task.addToPlans(tp1)
		task.addToPlans(tp2)
		task.save(flush: true)

		when: "task is removed"
		task.remove()

		then: "Task and TimePlannings are gone"
		0 == TimePlanning.count
		0 == Task.count
	}

	def "adding bookings to a subtask"() {
		given: "a subtask and some bookings"
		def task = new SubTask(name: "SubTask1", description: "sub1")
		def b1 = new Booking(date: new Date(), amount: 8, start: new Date().clearTime()-3, end: new Date().clearTime()-2)
		def b2 = new Booking(date: new Date(), amount: 8, start: new Date().clearTime()-2, end: new Date().clearTime()-1)
		def b3 = new Booking(date: new Date(), amount: 10, start: new Date().clearTime()-1, end: new Date().clearTime())

		when: "bookings are added and subtask is saved"
		task.addToBookings b1
		task.addToBookings b2
		task.addToBookings b3
		task.save(flush: true)
		def taskr = Task.get task.id

		then: "bookings are saved and correctly summed up in subtask"
		3 == Booking.count
		26 == task.timeBudgetUsed
		26 == taskr.timeBudgetUsed
	}

	def "evaluating a tree of tasks" () {
		given: "some compoundTasks and subtasks with some bookings"
		def prj = new CompoundTask(name: "Projekt", description: "prj")
		def ctask = new CompoundTask(name: "CompoundTask", description: "ct")
		def stask1 = new SubTask(name: "SubTask1", description: "sub1")
		def stask2 = new SubTask(name: "SubTask2", description: "sub2")
		def stask3 = new SubTask(name: "SubTask3", description: "sub3")
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

		when: "everything is connected and saved"
		prj.save(flush: true)
		prj = CompoundTask.get prj.id

		then: "timeBudgetUsed is correctly evaluated and saved"
		2 == CompoundTask.count
		3 == SubTask.count
		6 == Booking.count
		40 == prj.timeBudgetUsed
		28 == ctask.timeBudgetUsed
	}

	def "removing a tree of tasks" () {
		given: "some compoundTasks and subtasks with some bookings"
		def prj = new CompoundTask(name: "Projekt", description: "prj")
		def ctask = new CompoundTask(name: "CompoundTask", description: "ct")
		def stask1 = new SubTask(name: "SubTask1", description: "sub1")
		def stask2 = new SubTask(name: "SubTask2", description: "sub2")
		def stask3 = new SubTask(name: "SubTask3", description: "sub3")
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

		when: "top level task is removed"
		prj.remove()

		then: "everything is cleared from the database"
		0 == Booking.count
		0 == SubTask.count
		0 == CompoundTask.count
	}

	def "compare plan to bookings" () {
		given: "a task with plans and bookings"
		def task = new SubTask(name: "SubTask1", description: "sub1")
		def b1 = new Booking(date: new Date(), amount: 8, start: new Date().clearTime()-3, end: new Date().clearTime()-2)
		def b2 = new Booking(date: new Date(), amount: 8, start: new Date().clearTime()-2, end: new Date().clearTime()-1)
		def b3 = new Booking(date: new Date(), amount: 10, start: new Date().clearTime()-1, end: new Date().clearTime())
		def tp1 = new TimePlanning(tstamp: new Date().next(), description: "Start nächste Woche!", timeBudgetPlan: 30, start: new Date().clearTime()+7)
		def tp2 = new TimePlanning(tstamp: new Date(), description: "Start morgen!", timeBudgetPlan: 20, start: new Date().clearTime().next())
		task.addToPlans(tp1)
		task.addToPlans(tp2)
		task.addToBookings b1
		task.addToBookings b2
		task.addToBookings b3

		when: "subtask is saved"
		task.save(flush: true)
		task = Task.get task.id

		then: "difference of actual plan and bookings should be calculatable"
		4 == task.diffPlanUse
	}

	def "find top level element in task tree" () {
		given: "some compoundTasks and subtasks"
		def prj = new CompoundTask(name: "Projekt", description: "prj")
		def ctask = new CompoundTask(name: "CompoundTask", description: "ct")
		def stask1 = new SubTask(name: "SubTask1", description: "sub1")
		def stask2 = new SubTask(name: "SubTask2", description: "sub2")
		def stask3 = new SubTask(name: "SubTask3", description: "sub3")

		prj.addToSubtasks ctask
		prj.addToSubtasks stask3
		ctask.addToSubtasks stask1
		ctask.addToSubtasks stask2

		prj.save(flush: true)

		when: "looking for top level task from different starting points"
		def top1 = prj.topLevelTask
		def top2 = stask2.topLevelTask

		then: "it should be found"
		prj == top1
		prj == top2
	}

	def setup() {
	}

	def cleanup() {
	}
}
