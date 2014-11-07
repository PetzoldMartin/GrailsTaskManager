class UrlMappings {

	static mappings = {
		"/books"(resources:"book")
		"/customers"(resources:"customer")
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
