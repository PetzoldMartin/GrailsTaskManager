package de.fh_zwickau.spv.rest

class ExamsRestController {

    def index() { 
		render "Exams index $params"
		
	}
	
	def show() { 
		if(! params.id) 
			forward action: "index"
		else
			render "Exams show $params"
	}
	
	def save() { 
		render "Exams save $params"
	}
	
	def update() { 
		render "show $params"
	}
	
	def delete() { 
		render "show $params"
	}
}
