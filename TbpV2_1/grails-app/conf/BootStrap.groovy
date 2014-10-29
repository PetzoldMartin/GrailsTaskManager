import de.fh_zwickau.pti.tbpv2.CompoundTask

class BootStrap {

    def init = { servletContext ->
		if(!CompoundTask.count()) {
			def prj = new CompoundTask(name: "Projekt", description: "Top Level Projekt-Task, bitte editieren")
			prj.save flush: true
		}
    }
    def destroy = {
    }
}
