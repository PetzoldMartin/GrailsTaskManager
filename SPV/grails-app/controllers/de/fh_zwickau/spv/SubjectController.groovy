package de.fh_zwickau.spv



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SubjectController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Subject.list(params), model:[subjectInstanceCount: Subject.count()]
    }

    def show(Subject subjectInstance) {
        respond subjectInstance
    }

    def create() {
        respond new Subject(params)
    }

    @Transactional
    def save(Subject subjectInstance) {
        if (subjectInstance == null) {
            notFound()
            return
        }

        if (subjectInstance.hasErrors()) {
            respond subjectInstance.errors, view:'create'
            return
        }

        subjectInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'subject.label', default: 'Subject'), subjectInstance.id])
                redirect subjectInstance
            }
            '*' { respond subjectInstance, [status: CREATED] }
        }
    }

    def edit(Subject subjectInstance) {
        respond subjectInstance
    }

    @Transactional
    def update(Subject subjectInstance) {
        if (subjectInstance == null) {
            notFound()
            return
        }

        if (subjectInstance.hasErrors()) {
            respond subjectInstance.errors, view:'edit'
            return
        }

        subjectInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Subject.label', default: 'Subject'), subjectInstance.id])
                redirect subjectInstance
            }
            '*'{ respond subjectInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Subject subjectInstance) {

        if (subjectInstance == null) {
            notFound()
            return
        }

        subjectInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Subject.label', default: 'Subject'), subjectInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'subject.label', default: 'Subject'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
