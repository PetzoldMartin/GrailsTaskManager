package de.fh_zwickau.spv

import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured;
import grails.transaction.Transactional


@Transactional(readOnly = true)
@Secured(['ROLE_STUDENT', 'ROLE_LECTURER'])
class ExamResultController {
	
	def springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
		def user = springSecurityService.getCurrentUser()
		def query
		if(user instanceof Student) {
			query = ExamResult.where {
				student?.id == user?.id
			}
		} else {
			query = ExamResult.where {
				exam?.lecturer?.id == user?.id
			}
		}
        params.max = Math.min(max ?: 10, 100)
		def results = query.list(params)
        respond results, model:[examResultInstanceCount: results.size()]
    }

    def show(ExamResult examResultInstance) {
        if(mayAccess(examResultInstance)) {
			respond examResultInstance
        } else {
			render(view: '/login/denied') 
        }
    }
	
    @Secured(['ROLE_LECTURER'])
	def create() {
        respond new ExamResult(params)
    }

    @Transactional
    @Secured(['ROLE_LECTURER'])
    def save(ExamResult examResultInstance) {
        if (examResultInstance == null) {
            notFound()
            return
        }

        if (examResultInstance.hasErrors()) {
            respond examResultInstance.errors, view:'create'
            return
        }

        examResultInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'examResult.label', default: 'ExamResult'), examResultInstance.id])
                redirect examResultInstance
            }
            '*' { respond examResultInstance, [status: CREATED] }
        }
    }

    @Secured(['ROLE_LECTURER'])
    def edit(ExamResult examResultInstance) {
        respond examResultInstance
    }

    @Transactional
    def update(ExamResult examResultInstance) {
        if (examResultInstance == null) {
            notFound()
            return
        }

        if (examResultInstance.hasErrors()) {
            respond examResultInstance.errors, view:'edit'
            return
        }

        examResultInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ExamResult.label', default: 'ExamResult'), examResultInstance.id])
                redirect examResultInstance
            }
            '*'{ respond examResultInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(ExamResult examResultInstance) {

        if (examResultInstance == null) {
            notFound()
            return
        }

        examResultInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ExamResult.label', default: 'ExamResult'), examResultInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }
	
	protected boolean mayAccess(ExamResult examResultInstance) {
		def user = springSecurityService.getCurrentUser()
		if(user instanceof Student) {
			user?.id == examResultInstance?.student?.id
		} else {
			user?.id == examResultInstance?.exam?.lecturer?.id
		}
	}

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'examResult.label', default: 'ExamResult'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
