package quoter.services.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.Table

@Entity
@Table(
    name = "currencies",
    indexes = [Index(name = "IDX_currencies_id", columnList = "code")]
)
data class Currency(
    @Id val code: CurrencyCode,
    val rate: Double
)