package com.zek.cryptotracker.crypto.data.mappers

import com.zek.cryptotracker.crypto.data.networking.dto.CoinDto
import com.zek.cryptotracker.crypto.data.networking.dto.CoinPriceDto
import com.zek.cryptotracker.crypto.domain.Coin
import com.zek.cryptotracker.crypto.domain.CoinPrice
import java.time.Instant
import java.time.ZoneId

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

fun CoinPriceDto.toCoinPrice(): CoinPrice {
    return CoinPrice(
        priceUsd = priceUsd,
        dateTime = Instant.ofEpochMilli(time)
            .atZone(ZoneId.of("UTC"))
    )
}