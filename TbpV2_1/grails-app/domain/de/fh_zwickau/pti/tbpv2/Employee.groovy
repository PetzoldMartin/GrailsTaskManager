package de.fh_zwickau.pti.tbpv2

class Employee {
	String loginId
	String passwordHash
    static constraints = {
		loginId size: 3..10, unique: true, blank: false
		passwordHash bindable: false
    }
}
