package de.fh_zwickau.spv.rest

class ExamResultsRestController {

    def index() { 
		render "ExamResult index $params"
		
	}
	
	def show() { 
		render "ExamResult show $params"
	}
	
	def save() { 
		render "ExamResult save $params"
	}
	
	def update() { 
		render "ExamResult update $params"
	}
	
	def delete() { 
		render "ExamResult delete $params"
	}
}
