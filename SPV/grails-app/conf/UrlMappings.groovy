class UrlMappings {



	static mappings = {
		"/$controller/$action?/$id?(.$format)?"{ 
			constraints { 
				// apply constraints here
				} 
			}

		"/rest/"(controller: 'spv')
		def resources = [
			'students',
			'examResults',
			'lecturers',
			'exams',
			'subjects'
		]
		def actions = [
			'show',
			'save',
			'update',
			'delete'
		]
		// loop over all multi instance resources
		for(resource in resources) {
			"/rest/$resource/$id?"(controller: "${resource}Rest")
			for(act in actions) {
				"/rest/${resource}/${act}/$id?"(controller: "${resource}Rest", action: act)
			}
		}
		"/rest/search/"(controller: 'searchrest', action: 'search')
		"/"(view:"/index")
		"500"(view:'/error')
	}
}
