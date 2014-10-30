import org.example.Race
import org.example.Registration
import org.example.Runner
import org.example.Tag
import org.example.Task
import org.example.Customer
import org.junit.internal.runners.statements.FailOnTimeout;
import grails.util.GrailsUtil;

class BootStrap {

    def init = { servletContext ->
		def workTag = new Tag(name: "Work").save(failOnError: true)
		def homeTag = new Tag(name: "Home").save(failOnError: true)
		
		def task = new Task(
					summary: "Do 2nd Intro screencast",
					details: "Create the second intro screencast for Grails")
		task.addToTags(workTag)
		task.addToTags(homeTag)
		task.save(failOnError: true)
		
		def Customer = new Customer(
			firstName: "Heinz",
			lastName: "peter",
			
			gender: "male"  
			).save(failOnError: true)
		
		switch(GrailsUtil.environment){
			case "development":
			def jane = new Runner(
			firstName:"Jane",
			lastName:"Doe",
			dateOfBirth:(new Date() - 365*30),
			gender:"F",
			address:"123 Main St",
			city:"Goose",
			state:"NC",
			zipcode:"12345",
			email:"jane@whereever.com"
			)
			jane.save()
			if(jane.hasErrors()){
			println jane.errors
			}
			
			def trot = new Race(
			name:"Turkey Trot",
			startDate:(new Date() + 90),
			city:"Duck",
			state:"NC",
			distance:5.0,
			cost:20.0,
			maxRunners:350
			)
			trot.save()
			if(trot.hasErrors()){
					println trot.errors
				}
			def reg = new Registration(
			paid:false,
			runner:jane,
			race:trot
			)
			reg.save()
			if(reg.hasErrors()){
			println reg.errors
			}
			
			break
			
			case "production" : break
			}
    		
    }
    def destroy = {
    }
}
