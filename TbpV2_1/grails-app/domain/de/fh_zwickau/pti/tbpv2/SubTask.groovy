package de.fh_zwickau.pti.tbpv2

class SubTask extends Task {

	static hasMany = [bookings : Booking]
	
	int getTimeBudgetUsed() {
		if(!bookings || bookings.empty) {
			0
		} else {
			int used = 0
			for(Booking b in bookings) {
				used += b.amount
			}
			used
		}
	}
	
	int getTimeBudgetPlaned() {
		if(!plans || plans.empty) {
			0
		} else {
			int used = timeBudgetPlan
			
			used
		}
	}

	@Override
	def remove() {
		bookings.each {
			if(it.id)
				it.delete()
		}
		if(bookings)
			bookings.clear()
		super.remove()
		if(id)
			delete()
	}

	static constraints = {
	}
}
