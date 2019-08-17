package quoter.services.serviceA

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component


@Component
@ConfigurationProperties("quoter")
class QuoterConfiguration {

    lateinit var apiVersion: String
    lateinit var urlPrefix: String
    val security = Security()

    class Security {
        lateinit var secret: String
        lateinit var expirationTime: String
        lateinit var tokenPrefix: String
        lateinit var headerString: String
        lateinit var signUpUrl: String
    }

}
