package quoter.services.model

import javax.persistence.*

@Entity
@Table(
    name = "assets",
    indexes = [Index(name = "IDX_assets_id", columnList = "code")]
)
data class Asset(
    @Id val code: AssetCode,
    val spot: Double,
    val volatility: Double,
    @ManyToOne val currency: Currency
)
