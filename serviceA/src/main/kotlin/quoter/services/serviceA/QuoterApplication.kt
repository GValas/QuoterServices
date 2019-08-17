package quoter.services.serviceA

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
@ComponentScan( // where to register components, could use quoter.*
    basePackages = [
        "quoter.services.helper",
        "quoter.services.serviceA.*",
        "quoter.services.serviceA"
    ]
)
@EntityScan( // where to get register entities, could use quoter.*
    basePackages = [
        "quoter.services.model",
        "quoter.services.serviceA.*"
    ]
)
class QuoterApplication {
    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}

fun main(args: Array<String>) {
    runApplication<QuoterApplication>(*args)
}
