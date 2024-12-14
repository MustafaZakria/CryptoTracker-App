package com.zek.cryptotracker.crypto.presentation.models

import android.icu.number.NumberFormatter
import androidx.annotation.DrawableRes
import com.zek.cryptotracker.crypto.domain.Coin
import com.zek.cryptotracker.util.getDrawableIdForCoin
import java.text.NumberFormat
import java.util.Locale

data class CoinUi(
    val id: String,
    val rank: Int,
    val name: String,
    val symbol: String,
    val marketCapUsd: DisplayableNumber,
    val priceUsd: DisplayableNumber,
    val changePercent24Hr: DisplayableNumber,
    @DrawableRes val iconRes: Int
)

data class DisplayableNumber(
    val value: Double,
    val formatted: String
)

fun Coin.toCoinUi(): CoinUi {
    return CoinUi(
        id = id,
        name = name,
        symbol = symbol,
        rank = rank,
        priceUsd = priceUsd.toDisableNumber(),
        marketCapUsd = marketCapUsd.toDisableNumber(),
        changePercent24Hr = changePercent24H.toDisableNumber(),
        iconRes = getDrawableIdForCoin(symbol)
    )
}

fun Double.toDisableNumber(): DisplayableNumber {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }
    return DisplayableNumber(
        this,
        formatter.format(this)
    )
}
