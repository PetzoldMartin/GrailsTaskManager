package de.fh_zwickau.pti.tbpv2

import grails.transaction.Transactional

@Transactional(readOnly=true)
class TaskService {

	/**
	 * baut eine depth-first organisierte Liste aller tasks: <br/>
	 * map mit 
	 * <ul><li>maxlevel: Tiefe des Task Tree und</li> 
	 * <li>tasklist: depth-first organisierte Liste von maps, 
	 * die jeweils die Information enthalten:
	 * <ul><li>level: Tiefe der Task und</li> 
	 * 	<li>task: das Task-Objekt</li></ul></ul>
	 * @return model map
	 */
	def getTaskTree() {
		List list = Task.findAllBySuperTaskIsNull()
		Map taskmap = [:]
		List tasklist = []
		int maxlevel = 0
		for(task in list) {
			// depth first durch die Tasks
//			Task task = list[0].topLevelTask
			maxlevel = descendTaskTree(task, tasklist, 0)
		}
		taskmap << [maxlevel:maxlevel]
		taskmap << [tasklist:tasklist]
	}

	/**
	 * rekursiver Abstieg durch den Taskbaum und Eintrag in eine Liste von Tasks.
	 * Erst die Subtasks, dann die CompoundTasks mit den Teilbäumen
	 * 
	 * @param task aktuelle task
	 * @param tasklist Liste, in die alle tasks eingetragen werden
	 * @param level aktuelle Tiefe
	 * @return maximale Schachtelungstiefe
	 */
	int descendTaskTree(CompoundTask task, List tasklist, int level) {
		def l = level
		def subtasks = []
		def compoundtasks = []
		tasklist << makeTaskMap(task, level)
		for(t in task.subtasks) {
			if(t instanceof SubTask)
				subtasks << t
			else
				compoundtasks << t
		}
		for(t in subtasks) {
			l = Math.max(l, descendTaskTree(t, tasklist, level + 1))
		}
		for(t in compoundtasks) {
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
