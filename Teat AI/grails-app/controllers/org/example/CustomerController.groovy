package org.example



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON

@Transactional(readOnly = true)
class CustomerController {

	static defaultAction = "list"
	
	static allowedMethods = [ajaxList: "GET", ajaxSave: "POST", ajaxComplete: "POST"]
	
	   /**
		* Query for all incomplete Customers and return them as a JSON array
		*/
	   def ajaxList() {
		   render Customer.findAll( ) as JSON
	   }
	
	   /**
		* Get the description from the request's JSON structure, then return
		* the current list of Customers
		*/
	   def ajaxSave() {
		   def Customer = new Customer(
			   description : request.JSON.description,
			   complete : false
		   ).save( failOnError : true )
	
		   ajaxList()
	   }
	
	   /**
		* Mark whichever Customer desired complete
		*/
	   def ajaxComplete() {
		   def Customer = Customer.get( params.id )
		   
		   if ( Customer )
		   {
			   Customer.complete = true
			   Customer.save()
		   }
	
		   render ""
	   }
	
	   def list() {
		   def list = Customer.list()
		   [list:list]
	   }
	   
	   def list2() {
		   def list = Customer.list()
		   [list:list]
	   }
}
