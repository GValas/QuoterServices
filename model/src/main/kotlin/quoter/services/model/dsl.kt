package quoter.services.model

import java.time.LocalDateTime

class AssetBuilder {

    var assetCode = AssetCode.BNPP
    var spot = 0.0
    var volatility = 0.0
    var currency = currency {
        currencyCode = CurrencyCode.EUR
        rate = 0.0
    }

    fun build() = Asset(assetCode, spot, volatility, currency)

}

fun asset(init: AssetBuilder.() -> Unit) = AssetBuilder().apply(init).build()

class CurrencyBuilder {
    var currencyCode = CurrencyCode.EUR
    var rate = 0.0
    fun build() = Currency(currencyCode, rate)
}

fun currency(init: CurrencyBuilder.() -> Unit) = CurrencyBuilder().apply(init).build()

class ContractBuilder {

    var nominal = 0.0
    var maturity = LocalDateTime.of(2000, 1, 1, 0, 0, 0)
    var strike = 0.0
    var vanillaType = VanillaType.CALL
    var exerciseMode = ExerciseMode.EUROPEAN
    var underlying = asset {

    }
    var currency = currency {

    }

    fun build() = Contract(nominal, maturity, strike, vanillaType, exerciseMode, underlying, currency)
}

fun contract(init: ContractBuilder.() -> Unit) = ContractBuilder().apply(init).build()

