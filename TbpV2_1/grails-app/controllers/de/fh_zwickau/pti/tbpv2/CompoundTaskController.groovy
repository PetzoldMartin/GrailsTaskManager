package de.fh_zwickau.pti.tbpv2



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CompoundTaskController {
	
	TestDataService testDataService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond CompoundTask.list(params), model:[compoundTaskInstanceCount: CompoundTask.count()]
    }

    def show(CompoundTask compoundTaskInstance) {
        respond compoundTaskInstance
    }

    def create() {
        respond new CompoundTask(params)
    }

    @Transactional
    def save(CompoundTask compoundTaskInstance) {
        if (compoundTaskInstance == null) {
            notFound()
            return
        }

        if (compoundTaskInstance.hasErrors()) {
            respond compoundTaskInstance.errors, view:'create'
            return
        }

        compoundTaskInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'compoundTask.label', default: 'CompoundTask'), compoundTaskInstance.id])
                redirect compoundTaskInstance
            }
            '*' { respond compoundTaskInstance, [status: CREATED] }
        }
    }

    def edit(CompoundTask compoundTaskInstance) {
        respond compoundTaskInstance
    }

    @Transactional
    def update(CompoundTask compoundTaskInstance) {
        if (compoundTaskInstance == null) {
            notFound()
            return
        }

        if (compoundTaskInstance.hasErrors()) {
            respond compoundTaskInstance.errors, view:'edit'
            return
        }

        compoundTaskInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'CompoundTask.label', default: 'CompoundTask'), compoundTaskInstance.id])
                redirect compoundTaskInstance
            }
            '*'{ respond compoundTaskInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(CompoundTask compoundTaskInstance) {

        if (compoundTaskInstance == null) {
            notFound()
            return
        }

        compoundTaskInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'CompoundTask.label', default: 'CompoundTask'), compoundTaskInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }
	
//	@Transactional
	def genTestData() {
		testDataService.generateTestData()
		redirect action:"index", method:"GET" 
	}

//	@Transactional
	def delTestData() {
//		Task task = CompoundTask.findByName "exproject"
//		task.remove()
		testDataService.deleteTestData()
		redirect action:"index", method:"GET" 
	}

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'compoundTask.label', default: 'CompoundTask'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
