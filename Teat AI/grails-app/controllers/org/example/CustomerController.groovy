package org.example



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

class CustomerController{
	static defaultAction = "list"

	static scaffold = Customer
	
	def list() {
		def list = Customer.list()
		[list:list]
	}
	
	def list2() {
		def list = Customer.list()
		[list:list]
	}
	
	
	
    
	
}
