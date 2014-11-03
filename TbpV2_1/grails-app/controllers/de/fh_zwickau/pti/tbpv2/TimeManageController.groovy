package de.fh_zwickau.pti.tbpv2

class TimeManageController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	
    def index() {
		def tasks=Task.getAll()
		[tasks: tasks]
	}
	
	
	
	
}
