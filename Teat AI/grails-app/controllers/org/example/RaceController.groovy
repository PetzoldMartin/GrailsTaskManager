package org.example



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RaceController {

	def scaffold = Race
	
	
}
