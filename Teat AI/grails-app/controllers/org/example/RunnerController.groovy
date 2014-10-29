package org.example



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RunnerController {

	def scaffold = true
}
