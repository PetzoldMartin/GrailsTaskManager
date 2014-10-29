package org.example



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CustomerController {
	static defaultAction = "list"

	static scaffold = Customer
	
	def list() {
		def list = Customer.list()
		[list:list]
	}
    
	
}
