package de.fh_zwickau.spv

class Student extends User {

	static hasMany = [results: ExamResult]
	static mappedBy = [results: 'student']
	
	def initRole() {
		def role = Role.findByAuthority('ROLE_STUDENT')
		UserRole.create this, role, true
	}
	
    static constraints = {
    }
}
