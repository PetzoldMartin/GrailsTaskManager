package de.fh_zwickau.spv

class Exam {
	
	Lecturer lecturer
	Date date = new Date()
	
	static belongsTo = [subject: Subject, lecturer: Lecturer]
	static hasMany = [results: ExamResult]
	static mappedBy = [results: 'exam']

    static constraints = {
		results nullable: true
    }
}
