import de.fh_zwickau.spv.Role
import de.fh_zwickau.spv.User
import de.fh_zwickau.spv.UserRole

class BootStrap {

    def init = { servletContext ->
		def testUser = new User(username: 'me', password: 'hi', name: 'testuser')
		testUser.save flush: true
		
		def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
		def lecturerRole = new Role(authority: 'ROLE_LECTURER').save(flush: true)
		def studentRole = new Role(authority: 'ROLE_STUDENT').save(flush: true)
		
		UserRole.create testUser, adminRole, true
    }
	
    def destroy = {
    }
}
