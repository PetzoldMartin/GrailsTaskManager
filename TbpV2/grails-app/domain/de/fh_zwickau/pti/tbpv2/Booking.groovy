package de.fh_zwickau.pti.tbpv2

class Booking {
	
	Date date = new Date()
	int amount
	Date start
	Date end 
	
	static belongsTo = [task : SubTask]

    static constraints = {
    }
}
