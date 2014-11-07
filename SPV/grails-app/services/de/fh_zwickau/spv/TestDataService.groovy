package de.fh_zwickau.spv

import grails.transaction.Transactional

@Transactional
class TestDataService {
	def subjects = ["Netzwerke", "OOSY", "MoRS", "Analysis"]
	def lecturers = ["Müller", "Meier", "Schulze"]
	def students = ["Fredi", "Alex", "Paula", "Guiseppe", "Theo"]
	
    def createTestData() {
		Random random = new Random()
		if(! User.findByName('Guiseppe')) {
			println "create test data"
			lecturers.each {
				def l = new Lecturer(name: it, username: it, password: 'topsecret')
				l.save flush:true
				l.initRole()
			}
			students.each {
				def s = new Student(name: it, username: it, password: 'topsecret')
				s.save flush: true
				s.initRole()
			}
			subjects.eachWithIndex {sub, ix ->
				def s = new Subject(name: sub)
				def ls = Lecturer.list() 
				def ex1 = new Exam(lecturer: ls[ix % 3], subject: s, date: new Date() - 100)
				s.addToExams ex1
				def ex2 = new Exam(lecturer: ls[ix % 3], subject: s, date: new Date() + 100)
				s.addToExams ex2
				def studs = Student.list()
				2.times {
					def i = random.nextInt(5)
					def er = new ExamResult(student: studs[i], grade: random.nextInt(4) + 1	)
					ex1.addToResults er
					i = random.nextInt(5)
					er = new ExamResult(student: studs[i], grade: random.nextInt(3) + 1)
					ex2.addToResults er
				}
//				s.validate()
//				s.errors.each {println it}
				s.save flush:true
			}
		}
    }
}
