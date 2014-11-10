package de.fh_zwickau.spv.rest

import grails.converters.XML
import grails.converters.JSON
import org.codehaus.groovy.grails.web.metaclass.WithFormMethod;

class SpvController {
	
	def restfulRenderService

	def index() {
		def model = [
			_links: [
				index: '/SPV/rest/',
				students: '/SPV/rest/students',
				lecturers: '/SPV/rest/lecturers',
				subjects: '/SPV/rest/subjects',
				search: '/SPV/rest/search',
				home: '/SPV/'
			]
		]
		def halmodel = restfulRenderService.halModel(model)
		render(model, halmodel)
	}
	
	protected render (model, halmodel) {
		// wertet ?format=xxx aus
		withFormat {
			json { render model as JSON  }
			xml {  render model as XML  }
			hal { render halmodel as XML }
			halj { render halmodel as JSON }
			'*'	{  respond 'hurz', model: [links: model._links] }
		}
	}
}
