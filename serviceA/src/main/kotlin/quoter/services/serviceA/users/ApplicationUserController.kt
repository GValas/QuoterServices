package quoter.services.serviceA.users


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping
class ApplicationUserController(
        private val applicationUserRepository: ApplicationUserRepository,
        private val bCryptPasswordEncoder: BCryptPasswordEncoder) {

    @PostMapping("/sign-up")
    fun signUp(@RequestBody user: ApplicationUser) {
        user.password = bCryptPasswordEncoder.encode(user.password)
        applicationUserRepository.save(user)
    }
}