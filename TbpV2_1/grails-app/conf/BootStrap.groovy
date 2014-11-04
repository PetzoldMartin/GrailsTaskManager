import groovy.json.internal.Sys;
import de.fh_zwickau.pti.tbpv2.Booking
import de.fh_zwickau.pti.tbpv2.CompoundTask
import de.fh_zwickau.pti.tbpv2.SubTask

class BootStrap {

    def init = { servletContext ->
		if(!CompoundTask.count()) {
			def prj = new CompoundTask(name: "Projekt", description: "Top Level Projekt-Task, bitte editieren")
			def sbtsk = new SubTask(name: "subprojekt", description: "testsubtask")
			def booking = new Booking(date: new Date() - 365*30, amount: 20, start: new Date() - 365*28,end: new Date() - 365*26)
			prj.addToSubtasks sbtsk
			sbtsk.addToBookings booking
			prj.save flush: true	
			sbtsk.save flush: true
			booking.save flush: true
			
		}
		
    }
    def destroy = {
    }
}
