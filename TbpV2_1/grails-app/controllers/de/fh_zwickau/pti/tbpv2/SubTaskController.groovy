package de.fh_zwickau.pti.tbpv2



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SubTaskController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond SubTask.list(params), model:[subTaskInstanceCount: SubTask.count()]
    }

    def show(SubTask subTaskInstance) {
        respond subTaskInstance
    }

    def create() {
        respond new SubTask(params)
    }

    @Transactional
    def save(SubTask subTaskInstance) {
        if (subTaskInstance == null) {
            notFound()
            return
        }

        if (subTaskInstance.hasErrors()) {
            respond subTaskInstance.errors, view:'create'
            return
        }

        def s = subTaskInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'subTask.label', default: 'SubTask'), subTaskInstance.id])
                redirect subTaskInstance
            }
            '*' { respond subTaskInstance, [status: CREATED] }
        }
    }

    def edit(SubTask subTaskInstance) {
        respond subTaskInstance
    }

    @Transactional
    def update(SubTask subTaskInstance) {
        if (subTaskInstance == null) {
            notFound()
            return
        }

        if (subTaskInstance.hasErrors()) {
            respond subTaskInstance.errors, view:'edit'
            return
        }

        subTaskInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'SubTask.label', default: 'SubTask'), subTaskInstance.id])
                redirect subTaskInstance
            }
            '*'{ respond subTaskInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(SubTask subTaskInstance) {

        if (subTaskInstance == null) {
            notFound()
            return
        }

        subTaskInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'SubTask.label', default: 'SubTask'), subTaskInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'subTask.label', default: 'SubTask'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
