package de.fh_zwickau.spv



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class LecturerController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Lecturer.list(params), model:[lecturerInstanceCount: Lecturer.count()]
    }

    def show(Lecturer lecturerInstance) {
        respond lecturerInstance
    }

    def create() {
        respond new Lecturer(params)
    }

    @Transactional
    def save(Lecturer lecturerInstance) {
        if (lecturerInstance == null) {
            notFound()
            return
        }

        if (lecturerInstance.hasErrors()) {
            respond lecturerInstance.errors, view:'create'
            return
        }

        lecturerInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'lecturer.label', default: 'Lecturer'), lecturerInstance.id])
                redirect lecturerInstance
            }
            '*' { respond lecturerInstance, [status: CREATED] }
        }
    }

    def edit(Lecturer lecturerInstance) {
        respond lecturerInstance
    }

    @Transactional
    def update(Lecturer lecturerInstance) {
        if (lecturerInstance == null) {
            notFound()
            return
        }

        if (lecturerInstance.hasErrors()) {
            respond lecturerInstance.errors, view:'edit'
            return
        }

        lecturerInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Lecturer.label', default: 'Lecturer'), lecturerInstance.id])
                redirect lecturerInstance
            }
            '*'{ respond lecturerInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Lecturer lecturerInstance) {

        if (lecturerInstance == null) {
            notFound()
            return
        }

        lecturerInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Lecturer.label', default: 'Lecturer'), lecturerInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'lecturer.label', default: 'Lecturer'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
