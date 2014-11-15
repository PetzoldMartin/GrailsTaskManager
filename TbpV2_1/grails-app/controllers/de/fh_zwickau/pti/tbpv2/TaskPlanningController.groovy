package de.fh_zwickau.pti.tbpv2

import org.grails.databinding.BindUsing;
import grails.plugin.springsecurity.annotation.Secured;
import org.grails.databinding.BindingFormat;

import grails.transaction.Transactional;
import grails.validation.Validateable;

@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN','ROLE_USER','ROLE_VIEW'])
class TaskPlanningController {

	static defaultAction = "editTasks"

	TaskPlanningService taskPlanningService

	def createTasks() {
	}

	@Transactional
	def saveTasks() {
	}

	def editTasks() {
		taskPlanningService.taskInfos
	}

	@Transactional
	@Secured(['ROLE_ADMIN','ROLE_USER'])
	def updateTasks() {
 		println params
		withForm {
			def upd = params.get('in')
			def updKeys = upd?.keySet()?.grep(~/\d+/)
//		def updKeys = params?.keySet()?.grep(~/in\.\d+/)
			for(i in updKeys) {
				def cmd = new EditTaskCmd()
				def p = upd.get(i)
				bindData(cmd, p)
				if(cmd.validate()) {
					taskPlanningService.updateTask cmd
//				println "edit $cmd"
				} else {
//				println "validate failure on $cmd"
					cmd.errors.allErrors.each {println it}
				}
			}
			def create = params.get('cr')
			def createKeys = create?.keySet()?.grep(~/\d+/)
			for(i in createKeys) {
				def cmd = new CreateTaskCmd()
				def p = create.get(i)
				bindData(cmd, p)
				if(cmd.validate()) {
					taskPlanningService.createTask cmd
				} else {
//				println "validate failure on $cmd"
//				cmd.errors.allErrors.each {println it}
				}
			}
			forward action: "editTasks"
		}//.invalidToken {
//			render "invalid or duplicate form submission"
//		}
	}
}

@Validateable
class CreateTaskCmd {
	String name
	boolean compound
	String description
	long parent = 0
	int timeBudgetPlan = 0
	@BindingFormat('dd.MM.yyyy')
	Date start

	static constraints = {
		name size: 2..12, unique: true
		description size: 0..2000
		compound nullable: true
		parent nullable: true
		timeBudgetPlan nullable: true, min:0
		start nullable: true
	}

	def String toString() {
		"name: $name, ${compound? 'C' : 'S'}, parent: $parent, plan: $timeBudgetPlan, start: ${start?.format('dd.MM.yyyy')}, desc: $description"
	};
}

@Validateable
class EditTaskCmd extends CreateTaskCmd {
	long id
	long version = -1

	static constraints = {
		id min: 1l
		version min: 0l
		parent validator: {val, obj->
			val != obj.id
		}
	}

	def hasAttributeChanges(Task task) {
		task.name != name ||
				task.description != description
	}

	def hasPlanChanges(Task task) {
		task.timeBudgetPlan != timeBudgetPlan ||
				task.start != start
	}

	def hasSuperTaskChanges(Task task) {
		task.superTaskId != parent
	}

	def String toString() {
		"id $id:, version: $version, " + super.toString()
	}
}