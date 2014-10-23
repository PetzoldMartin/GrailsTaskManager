package de.fh_zwickau.pti.tbpv2

class SubTask extends Task {

	static hasMany = [bookings : Booking]

	int getTimeBudgetUsed() {
		if(bookings.empty) {
			0
		} else {
			int used = 0
			for(Booking b in bookings) {
				used += b.amount
			}
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
