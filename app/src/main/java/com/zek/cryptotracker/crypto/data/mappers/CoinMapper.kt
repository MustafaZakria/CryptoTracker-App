package com.zek.cryptotracker.crypto.data.mappers

import com.zek.cryptotracker.crypto.data.networking.dto.CoinDto
import com.zek.cryptotracker.crypto.domain.Coin

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        rank = rank,
        name = name,
        symbol = symbol,
        marketCapUsd = marketCapUsd,
        priceUsd = priceUsd,
        changePercent24H = changePercent24Hr
    )
}