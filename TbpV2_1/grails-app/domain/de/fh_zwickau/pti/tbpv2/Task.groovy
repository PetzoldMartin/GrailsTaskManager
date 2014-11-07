package de.fh_zwickau.pti.tbpv2

abstract class Task {

	String name
	String description

	SortedSet plans
	static hasMany = [plans:TimePlanning]
	static belongsTo = [superTask : CompoundTask]


	abstract int getTimeBudgetUsed()
	abstract int getTimeBudgetPlaned()

	def getDiffPlanUse() {
		getTimeBudgetPlan() - getTimeBudgetUsed()
	}

	def getTimeBudgetPlan() {
		if(!plans || plans.empty) {
			0
		} else {
			plans[-1].timeBudgetPlan
		}
	}

	def getStart() {
		if(!plans || plans.empty) {
			null
		} else {
			plans[-1].start
		}
	}

	def getSuperTaskId() {
		if(superTask)
			superTask.id
		else
			0
	}

	String getInfo() {
		if(superTask)
			"${superTask.name}"
		else
			''
	}

	def remove() {
		plans.each {
			if(it.id)
				it.delete()
		}
		if(plans)
			plans.clear()
	}

	def getTopLevelTask() {
		if(!superTask)
			this
		else
			superTask.topLevelTask
	}

	def isParent(Task t) {
		if(t == this) {
			true
		} else {
			def task = superTask
			while(task) {
				if(task == t) {
					return true
				}
				task = task.superTask
			}
		}
		false
	}

	static constraints = {
		name size: 2..12, unique: true
		description size: 0..2000
		superTask nullable: true //,
//		validator: { val, self ->
//			if(val &&  val.isParent(self))
//				"cyclic supertask reference"
//			else
//				true
//		}
	}
}
