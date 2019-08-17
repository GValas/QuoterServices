package quoter.services.serviceA.services

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import quoter.services.serviceA.repositories.ApplicationUserRepository
import java.util.Collections.emptyList

@Service
class UserDetailsServiceImpl(private val applicationUserRepository: ApplicationUserRepository) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val (username1, password) = applicationUserRepository.findByUsername(username)
        return User(username1, password, emptyList<GrantedAuthority>())
    }
}