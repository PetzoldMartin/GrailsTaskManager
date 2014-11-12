class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?(.$format)?"{ 
			constraints { 
				// apply constraints here
				} 
			}
		
		"/rest/"(controller: 'spv')
		"/rest/students"(resources: 'studentsRest') {
			"/examResults"(resources: 'examResultsRest')
		}
		"/rest/lecturers"(resources: 'lecturersRest') {
			"/exams"(resources:  'examsRest')
		}
		"/rest/subjects"(resources: 'subjectsRest')
		"/rest/search/"(controller: 'searchrest', action: 'search')

		"/"(view:"/index")
		"500"(view:'/error')

	}
}
