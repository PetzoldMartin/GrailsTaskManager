package de.fh_zwickau.spv.rest

import grails.converters.JSON
import grails.converters.XML
import grails.transaction.Transactional

@Transactional
class RestfulRenderService {

    def halModel(Map model) {
		def hal = [:]
		for(key in model.keySet()) {
			if(key != '_links' && key != '_embedded') {
				def val = model[key]
				if(val instanceof List) {
					def vals = []
					for(listElem in val) {
						if(listElem instanceof Map) {
							vals << halModel(listElem)
						} else {
							vals << listElem
						}
					}
					hal.put key, vals
				} else {
					hal.put key, val
				}
			}
		}
		hal << halLinks(model._links)
		// TODO embedded
		hal
    }
	
	private halLinks(Map links) {
		if(links && links instanceof Map) {
			def l = [:]
			for(linkkey in links.keySet()) {
				l.put linkkey, [href: links[linkkey]]
			}
			[_links: l]
		} else {
			[:]
		}
	}
	
}
