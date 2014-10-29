package de.fh_zwickau.pti.tbpv2



import grails.test.mixin.*
import spock.lang.*

@TestFor(SubTaskController)
@Mock(SubTask)
class SubTaskControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
		params.id = 1
		params.name = "test task"
		params.description = 'description'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.subTaskInstanceList
            model.subTaskInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.subTaskInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def subTask = new SubTask()
			println "subTask: $subTask"
            subTask.validate()
            controller.save(subTask)

        then:"The create view is rendered again with the correct model"
            model.subTaskInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            subTask = new SubTask(params)
//			subTask.id = 1

            controller.save(subTask)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/subTask/show/1'
            controller.flash.message != null
            SubTask.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def subTask = new SubTask(params)
            controller.show(subTask)

        then:"A model is populated containing the domain instance"
            model.subTaskInstance == subTask
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def subTask = new SubTask(params)
            controller.edit(subTask)

        then:"A model is populated containing the domain instance"
            model.subTaskInstance == subTask
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/subTask/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def subTask = new SubTask()
            subTask.validate()
            controller.update(subTask)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.subTaskInstance == subTask

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            subTask = new SubTask(params)
//			subTask.id = 3
			subTask.save(flush: true)
            controller.update(subTask)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/subTask/show/$subTask.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/subTask/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def subTask = new SubTask(params).save(flush: true)

        then:"It exists"
            SubTask.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(subTask)

        then:"The instance is deleted"
            SubTask.count() == 0
            response.redirectedUrl == '/subTask/index'
            flash.message != null
    }
}
