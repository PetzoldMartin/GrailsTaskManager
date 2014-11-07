package de.fh_zwickau.spv

class Person {
	
	String name

    static constraints = {
		name blank: false, unique: true
    }
}
