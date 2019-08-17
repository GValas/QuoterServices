package quoter.services.model

import com.fasterxml.jackson.annotation.JsonValue

enum class AssetCode(val code: String) {
    BNPP("BNPP.PA"),
    SOGE("SOGN.PA");

    @JsonValue
    fun toValue(): String {
        return code
    }
}