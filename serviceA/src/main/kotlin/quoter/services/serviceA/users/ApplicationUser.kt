package quoter.services.serviceA.users

import javax.persistence.*

@Entity
@Table(
    name = "users",
    indexes = [Index(name = "IDX_users_id", columnList = "id")]
)
data class ApplicationUser(
    val username: String,
    var password: String,
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0
)