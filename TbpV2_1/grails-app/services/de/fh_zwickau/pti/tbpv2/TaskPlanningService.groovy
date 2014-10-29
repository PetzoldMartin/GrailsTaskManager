package de.fh_zwickau.pti.tbpv2

import javax.swing.JFormattedTextField.CommitAction;

import grails.transaction.Transactional

@Transactional
class TaskPlanningService {

	TaskService taskService

	def getTaskInfos() {
		def tasks = taskService.taskTree.tasklist
		def taskInfos = []
		def compoundTasks = []
		for(tle in tasks) {
			Task task = tle.task
			def taskInfo = [:]
			taskInfo.id = task.id
			taskInfo.version = task.version
			if(task.superTask) {
				taskInfo.parentId = task.superTask.id
			} else {
				taskInfo.parentId = 0
			}
			taskInfo.name = task.name
			taskInfo.description = task.description
			taskInfo.timeBudgetPlan = task.timeBudgetPlan
			taskInfo.timeBudgetUsed = task.timeBudgetUsed
			taskInfo.start = task.start
			taskInfo.compound = task instanceof CompoundTask
			if(task instanceof CompoundTask) {
				compoundTasks << [id: task.id, name: task.name]
			}
			taskInfo.level = tle.level
			taskInfos << taskInfo
		}
		[taskInfos: taskInfos, compoundTasks: compoundTasks]
	}

	def updateTask(EditTaskCmd cmd) {
		if(cmd.validate()) {
			Task task = Task.get(cmd.id)
			boolean commit = false
			if(task) {
				if(cmd.hasAttributeChanges(task)) {
					task.properties = cmd.properties
					commit = true
				}
				if(cmd.hasPlanChanges(task)) {
					TimePlanning tp = new TimePlanning(description: "planning changed", tstamp: new Date())
					tp.timeBudgetPlan = cmd.timeBudgetPlan
					tp.start = cmd.start
					task.addToPlans tp
					commit = true
				}
				if(cmd.hasSuperTaskChanges(task)) {
					if(cmd.parent > 0) {
						Task parent = Task.get(cmd.parent)
						if(parent) {
							task.superTask = parent
							commit = true
						}
					} else {
						task.superTask = null
						commit = true
					}
				}
				if(commit && task.validate())
					task.save()
			}
		}
	}

	def createTask(CreateTaskCmd cmd) {
		if(cmd.validate()) {
			Task task = cmd.compound ? new CompoundTask() : new SubTask()
			task.properties = cmd.properties
			if(cmd.timeBudgetPlan > 0 || cmd.start) {
				TimePlanning tp = new TimePlanning(description: "initial planning", tstamp: new Date())
				tp.timeBudgetPlan = cmd.timeBudgetPlan
				tp.start = cmd.start
				task.addToPlans tp
			}
			if(cmd.parent > 0) {
				Task parent = Task.get(cmd.parent)
				if(parent) {
					task.superTask = parent
				}
			}
			if(task.validate()) {
				task.save()
			} else {
				println "cmd validation failed for $task"
			}
		} else {
			println "cmd validation failed for $cmd"
		}
	}
}
