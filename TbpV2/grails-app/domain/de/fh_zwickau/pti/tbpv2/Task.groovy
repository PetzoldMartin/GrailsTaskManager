package de.fh_zwickau.pti.tbpv2

abstract class Task {

	String name
	String description

	SortedSet plans
	static hasMany = [plans:TimePlanning]
	static belongsTo = [superTask : CompoundTask]

	abstract int getTimeBudgetUsed()

	def getDiffPlanUse() {
		getTimeBudgetPlan() - getTimeBudgetUsed()
	}

	def getTimeBudgetPlan() {
		if(plans.empty) {
			0
		} else {
			plans[-1].timeBudgetPlan
		}
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

	static constraints = {
		name size: 2..12, unique: true
		description size: 0..2000
		superTask nullable: true
	}
}
