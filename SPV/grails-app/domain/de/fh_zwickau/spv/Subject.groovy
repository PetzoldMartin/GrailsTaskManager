package de.fh_zwickau.spv

class Subject {
	
	String name
	
	static hasMany = [exams: Exam]
	static mappedBy = [exams: 'subject']

    static constraints = {
		name blank: false, unique: true
		exams nullable: true
    }
}
