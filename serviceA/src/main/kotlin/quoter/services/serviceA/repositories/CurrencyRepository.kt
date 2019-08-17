package quoter.services.serviceA.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import quoter.services.model.Currency
import quoter.services.model.CurrencyCode

@Repository
interface CurrencyRepository : JpaRepository<Currency, CurrencyCode> {
    fun findByCode(code: CurrencyCode): List<Currency>
}