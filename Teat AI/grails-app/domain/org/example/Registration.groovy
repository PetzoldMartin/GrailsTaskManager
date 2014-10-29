package org.example

class Registration {
	//String name
	Date dateCreated //Note: this is a special name
	Date lastUpdated
	static belongsTo = [race:Race, runner:Runner]
	Boolean paid
	
    static constraints = {
		race()
		runner()
		paid()
		dateCreated()
		lastUpdated (nullable: true)
    }
	
	static mapping ={
		autoTimestamp false
	}
	def beforeInsert = {
	// your code goes here
	}
	def beforeUpdate = {
	// your code goes here
	}
	def beforeDelete = {
	// your code goes here
	}
	def onLoad = {
	// your code goes here
	}
}
