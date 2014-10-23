package de.fh_zwickau.pti.tbpv2

import grails.transaction.Transactional

@Transactional(readOnly=true)
class TaskService {

	def getTaskTree() {
		List list = CompoundTask.list max:1
		Map taskmap = [:]
		List tasklist = []
		int maxlevel = 0
		if(!list.empty) {
			// depth first durch die Tasks
			Task task = list[0].topLevelTask
			maxlevel = descendTaskTree(task, tasklist, 0)
		}
		taskmap << [maxlevel:maxlevel]
		taskmap << [tasklist:tasklist]
	}

	int descendTaskTree(CompoundTask task, List tasklist, int level) {
		def l = level
		tasklist << makeTaskMap(task, level)
		for(t in task.subtasks) {
			l = Math.max(l, descendTaskTree(t, tasklist, level + 1))
		}
		l
	}

	int descendTaskTree(SubTask task, List tasklist, int level) {
		tasklist << makeTaskMap(task, level)
		level
	}

	Map makeTaskMap(Task task, int level) {
		def map = [task:task]
		map << [level:level]
	}
}
