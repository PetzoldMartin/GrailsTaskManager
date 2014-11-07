import groovy.json.internal.Sys;
import de.fh_zwickau.pti.tbpv2.Booking
import de.fh_zwickau.pti.tbpv2.CompoundTask
import de.fh_zwickau.pti.tbpv2.SubTask
import de.fh_zwickau.pti.tbpv2.TimePlanning
import de.fh_zwickau.pti.tbpv2.User

class BootStrap {

    def init = { servletContext ->
		if(!CompoundTask.count()) {
			def prj = new CompoundTask(name: "Projekt", description: "Top Level Projekt-Task, bitte editieren")
			def uprj = new CompoundTask(name: "Saeuberung", description: "Saubermachen")
			
			def sbtsk = new SubTask(name: "subprojekt", description: "testsubtask")
			def sbtsk2 = new SubTask(name: "Kehren", description: "Kehren")
			def sbtsk3 = new SubTask(name: "Spuelen", description: "Spuelen")
			
			
			def booking = new Booking(date: new Date() - 365*30, amount: 20, start: new Date() - 365*28,end: new Date() - 365*26)
			def booking2 = new Booking(date: new Date() - 365*35, amount: 20, start: new Date() - 365*33,end: new Date() - 365*29)
			def tp1 = new TimePlanning(tstamp: new Date().next().clearTime(), description: "Start n�chste Woche!", timeBudgetPlan: 100, start: new Date().clearTime()+7)
			def tp2 = new TimePlanning(tstamp: new Date().next().clearTime(), description: "Start n�chste Woche!", timeBudgetPlan: 200, start: new Date().clearTime()+7)
			
			
			
			tp1.save flush: true
			tp2.save flush: true
			prj.save flush: true
			uprj.save flush: true	
			sbtsk.save flush: true
			sbtsk2.save flush: true
			sbtsk3.save flush: true
			
			
			booking.save flush: true
			booking2.save flush: true
			
			
			prj.addToSubtasks uprj
			prj.addToSubtasks sbtsk
			sbtsk.addToBookings booking
			sbtsk.addToBookings booking2
			sbtsk.addToPlans tp1
			sbtsk2.addToPlans tp2
			uprj.addToSubtasks sbtsk2
			uprj.addToSubtasks sbtsk3
			
			def prj2 = new CompoundTask(name: "Projekt2", description: "Top Level Projekt-Task, bitte editieren")
			prj2.save flush: true
			
			def testUser = new User(
				username: 'me',
				password: 'xx',
				name: 'testuser'
				)
			testUser.save flush: true
		}
		
    }
    def destroy = {
    }
}
