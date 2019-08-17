package quoter.services.serviceA.dataloader

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import quoter.services.helper.roundValueEx
import quoter.services.model.*
import quoter.services.model.CurrencyCode
import quoter.services.model.Currency
import quoter.services.serviceA.repositories.AssetRepository
import quoter.services.serviceA.repositories.ContractRepository
import quoter.services.serviceA.repositories.CurrencyRepository
import java.time.LocalDateTime
import java.util.*

@Component
class DataLoader(
        @Autowired private val contractRepository: ContractRepository,
        @Autowired private val currencyRepository: CurrencyRepository,
        @Autowired private val assetRepository: AssetRepository
) {

    init {
        loadCurrencies()
        loadStocks()
        loadContracts()
    }

    private fun loadStocks() {
        assetRepository.save(Asset(AssetCode.BNPP, 50.0, 0.3, currencyRepository.findByCode(CurrencyCode.EUR).first()))
        assetRepository.save(Asset(AssetCode.SOGE, 40.0, 0.4, currencyRepository.findByCode(CurrencyCode.EUR).first()))
    }

    private fun loadCurrencies() {
        currencyRepository.save(Currency(CurrencyCode.EUR, 0.08))
        currencyRepository.save(Currency(CurrencyCode.USD, 0.05))
    }

    private fun loadContracts() {
        val rand = Random()
        val currencies = currencyRepository.findAll().toList()
        val assets = assetRepository.findAll().toList()
        repeat(10) {
            with(rand) {
                contractRepository.save(
                        Contract(
                                nominal = (nextDouble() * 10000).roundValueEx(0),
                                maturity = LocalDateTime.of(2020 + nextInt(5), 1 + nextInt(12), 1 + nextInt(28), 0, 0, 0),
                                strike = (90 + nextDouble() * 20).roundValueEx(2),
                                vanillaType = VanillaType.values()[nextInt(VanillaType.values().size)],
                                exerciseMode = ExerciseMode.values()[nextInt(ExerciseMode.values().size)],
                                underlying = assets[nextInt(assets.size)],
                                currency = currencies[nextInt(currencies.size)]
                        )
                )
            }

        }
    }
}