package de.fh_zwickau.spv

class ExamResult {

	float grade
	Student student
	
	static belongsTo = [exam: Exam]
	
    static constraints = {
		grade min: 1.0f, max: 5.0f, nullable: false
    }
}
