package de.fh_zwickau.pti.tbpv2

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional


// Command Objekt wird gebraucht, um id aus dem Request auszulesen
class IdCommand {
	long id
}

@Transactional(readOnly = true)
class TaskController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	// injected objects
	def taskService

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		def tasks = taskService.getTaskTree()
		tasks
	}

	def show(IdCommand idc) {//Task taskInstance) {
		Task taskInstance = Task.get(idc.id)
		log.info "instance: $taskInstance"
		if(taskInstance)
			toSubclass taskInstance
	}

	protected void toSubclass(SubTask t) {
		forward controller: 'subTask', action: 'show', params: [subTaskInstance: t]
	}

	protected void toSubclass(CompoundTask t) {
		forward controller: 'compoundTask', action: 'show', params: [compoundTaskInstance: t]
	}


	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [
					message(code: 'compoundTask.label', default: 'CompoundTask'),
					params.id
				])
				redirect action: "index", method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}
}
