package quoter.services.serviceA.repositories

import org.springframework.data.jpa.repository.JpaRepository
import quoter.services.serviceA.model.ApplicationUser

interface ApplicationUserRepository : JpaRepository<ApplicationUser, Long> {
    fun findByUsername(username: String): ApplicationUser
}