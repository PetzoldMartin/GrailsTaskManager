package org.example
import grails.rest.*
@Resource(uri='/CustomerRest', formats=['json', 'xml'])
class Customer {

	String firstName, lastName
	Date birthday
	String gender
	String maritalStatus
	
    static constraints = {
		firstName (nullable:true) 
		lastName (nullable:true)
		birthday (nullable:true)
		gender (nullable:true)
		maritalStatus (nullable:true)
    }
}
