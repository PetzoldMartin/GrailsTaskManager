package de.fh_zwickau.spv.rest

import de.fh_zwickau.spv.Student
import grails.converters.JSON
import grails.converters.XML

class StudentsRestController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def restfulRenderService

	def index() {
		def studs = []
		for(student in Student.list()) {
			studs << [
				id: student.id,
				name: student.name,
				_links: [student: "/SPV/rest/students/show/${student.id}"]
			]
		}
		def model = [students: studs]
		def halmodel = restfulRenderService.halModel(model)
		format(model, halmodel)
	}

	def show() {
		def id = params.id
		if(id) {
			def student = Student.get(id)
			if(student) {
				def model = [
					id: id,
					name: student.name,
					_links: [
						examResults: "/SPV/rest/examResults/$id"
					]
				]
				def halmodel = restfulRenderService.halModel(model)
				format(model, halmodel)
			} else {
				render text: 'Not Found', status: 404
			}
		}
	}

	def save() {
		render "show $params"
	}

	def update() {
		render "show $params"
	}

	def delete() {
		render "show $params"
	}

	protected format (model, halmodel) {
		// wertet ?format=xxx aus
		withFormat {
			json { render model as JSON  }
			xml {  render model as XML  }
			hal { render halmodel as XML }
			halj { render halmodel as JSON }
			'*'	{  render model }
			//			'*'	{  respond 'hurz', model: [links: model._links] }
		}
	}
}
