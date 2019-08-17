package quoter.services.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(
    name = "contracts",
    indexes = [Index(name = "IDX_contracts_id", columnList = "id")]
)

data class Contract(
    val nominal: Double,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd HH:mm:ss")
    val maturity: LocalDateTime,
    val strike: Double,
    val vanillaType: VanillaType,
    val exerciseMode: ExerciseMode,
    @ManyToOne val underlying: Asset,
    @ManyToOne val currency: Currency,
    @Id val id: String = UUID.randomUUID().toString()
)