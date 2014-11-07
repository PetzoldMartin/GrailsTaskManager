package de.fh_zwickau.spv

class Lecturer extends User {
	
	static hasMany = [exams: Exam]

	def initRole() {
		def role = Role.findByAuthority('ROLE_LECTURER')
		UserRole.create this, role, true
	}
	
    static constraints = {
		exams nullable: true
    }
}
