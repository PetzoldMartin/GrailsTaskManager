package de.fh_zwickau.pti.tbpv2

class CompoundTask extends Task {

	static hasMany = [subtasks : Task]

	static constraints = {
	}
	@Override
	def remove() {
		subtasks.each {
			if(it.id)
				it.remove()
		}
		if(subtasks)
			subtasks.clear()
		super.remove()
		if(id)
			delete()
	}

	@Override
	int getTimeBudgetUsed() {
		if(!subtasks || subtasks.empty) {
			return 0
		} else {
			int used = 0
			for(Task t in subtasks) {
				used += t.timeBudgetUsed
			}
			return used
		}
	}
	
	@Override
	int getTimeBudgetPlaned() {
		if(!subtasks || subtasks.empty) {
			return 0
		} else {
			int used = 0
			for(Task t in subtasks) {
				used += t.getTimeBudgetPlaned()
			}
			return used
		}
	}
}
