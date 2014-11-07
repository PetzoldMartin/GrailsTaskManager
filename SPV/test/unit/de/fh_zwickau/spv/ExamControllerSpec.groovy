package de.fh_zwickau.spv



import grails.test.mixin.*
import spock.lang.*

@TestFor(ExamController)
@Mock(Exam)
class ExamControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.examInstanceList
            model.examInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.examInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def exam = new Exam()
            exam.validate()
            controller.save(exam)

        then:"The create view is rendered again with the correct model"
            model.examInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            exam = new Exam(params)

            controller.save(exam)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/exam/show/1'
            controller.flash.message != null
            Exam.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def exam = new Exam(params)
            controller.show(exam)

        then:"A model is populated containing the domain instance"
            model.examInstance == exam
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def exam = new Exam(params)
            controller.edit(exam)

        then:"A model is populated containing the domain instance"
            model.examInstance == exam
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/exam/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def exam = new Exam()
            exam.validate()
            controller.update(exam)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.examInstance == exam

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            exam = new Exam(params).save(flush: true)
            controller.update(exam)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/exam/show/$exam.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/exam/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def exam = new Exam(params).save(flush: true)

        then:"It exists"
            Exam.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(exam)

        then:"The instance is deleted"
            Exam.count() == 0
            response.redirectedUrl == '/exam/index'
            flash.message != null
    }
}
