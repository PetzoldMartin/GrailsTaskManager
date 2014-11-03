package de.fh_zwickau.pti.tbpv2

class TimeManageController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	TimeManageService timeManageService
	
    def index() {
		timeManageService.tasks
	}
	
	
	
	
}
