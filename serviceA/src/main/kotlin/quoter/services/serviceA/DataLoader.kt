package quoter.services.serviceA

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import quoter.services.model.asset
import quoter.services.model.contract
import quoter.services.model.currency
import quoter.services.helper.roundValueEx
import quoter.services.model.*
import quoter.services.model.CurrencyCode
import quoter.services.serviceA.contractsApi.AssetRepository
import quoter.services.serviceA.contractsApi.ContractRepository
import quoter.services.serviceA.contractsApi.CurrencyRepository
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

    private val eur = currency {
        currencyCode = CurrencyCode.EUR
        rate = 0.08
    }

    private val usd = currency {
        currencyCode = CurrencyCode.USD
        rate = 0.05
    }


    private fun loadStocks() {
        assetRepository.save(
                asset {
                    assetCode = AssetCode.BNPP
                    spot = 50.0
                    volatility = 0.3
                    currency = eur
                })
        assetRepository.save(
                asset {
                    assetCode = AssetCode.SOGE
                    spot = 40.0
                    volatility = 0.4
                    currency = eur
                })
    }

    private fun loadCurrencies() {
        currencyRepository.save(eur)
        currencyRepository.save(usd)
    }

    private fun loadContracts() {
        val rand = Random()
        val currencies = currencyRepository.findAll().toList()
        val assets = assetRepository.findAll().toList()
        repeat(10) {
            with(rand) {
                contractRepository.save(
                        contract {
                            nominal = (nextDouble() * 10000).roundValueEx(0),
                            maturity = LocalDateTime.of(2020 + nextInt(5), 1 + nextInt(12), 1 + nextInt(28), 0, 0, 0),
                            strike = (90 + nextDouble() * 20).roundValueEx(2),
                            vanillaType = VanillaType.values()[nextInt(VanillaType.values().size)],
                            exerciseMode = ExerciseMode.values()[nextInt(ExerciseMode.values().size)],
                            underlying = assets[nextInt(assets.size)],
                            currency = currencies[nextInt(currencies.size)]
                        }
                )
            }

        }
    }
}