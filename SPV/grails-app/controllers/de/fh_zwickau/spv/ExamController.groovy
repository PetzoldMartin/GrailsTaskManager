package de.fh_zwickau.spv



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ExamController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Exam.list(params), model:[examInstanceCount: Exam.count()]
    }

    def show(Exam examInstance) {
        respond examInstance
    }

    def create() {
        respond new Exam(params)
    }

    @Transactional
    def save(Exam examInstance) {
        if (examInstance == null) {
            notFound()
            return
        }

        if (examInstance.hasErrors()) {
            respond examInstance.errors, view:'create'
            return
        }

        examInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'exam.label', default: 'Exam'), examInstance.id])
                redirect examInstance
            }
            '*' { respond examInstance, [status: CREATED] }
        }
    }

    def edit(Exam examInstance) {
        respond examInstance
    }

    @Transactional
    def update(Exam examInstance) {
        if (examInstance == null) {
            notFound()
            return
        }

        if (examInstance.hasErrors()) {
            respond examInstance.errors, view:'edit'
            return
        }

        examInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Exam.label', default: 'Exam'), examInstance.id])
                redirect examInstance
            }
            '*'{ respond examInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Exam examInstance) {

        if (examInstance == null) {
            notFound()
            return
        }

        examInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Exam.label', default: 'Exam'), examInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'exam.label', default: 'Exam'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
