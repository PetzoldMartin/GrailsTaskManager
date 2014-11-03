package de.fh_zwickau.pti.tbpv2

class TimeManageController {

	def taskPlanningService
	
    def index() {
		def mymap = taskPlanningService.getTaskInfos()
		
		[myMapNameInGSP:mymap]
	}
	
	def test() {
		def map = [name: "Fox", sname: "Foxel"]
		map
	}
}
