package com.zek.cryptotracker.crypto.data.networking

import com.zek.cryptotracker.core.data.networking.constructUrl
import com.zek.cryptotracker.core.data.networking.safeCall
import com.zek.cryptotracker.core.domain.util.NetworkError
import com.zek.cryptotracker.core.domain.util.Result
import com.zek.cryptotracker.core.domain.util.map
import com.zek.cryptotracker.crypto.data.mappers.toCoin
import com.zek.cryptotracker.crypto.data.mappers.toCoinPrice
import com.zek.cryptotracker.crypto.data.networking.dto.CoinHistoryDto
import com.zek.cryptotracker.crypto.data.networking.dto.CoinsResponseDto
import com.zek.cryptotracker.crypto.domain.Coin
import com.zek.cryptotracker.crypto.domain.CoinDataSource
import com.zek.cryptotracker.crypto.domain.CoinPrice
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import java.time.ZoneId
import java.time.ZonedDateTime

class RemoteCoinDataSource(
    private val httpClient: HttpClient
) : CoinDataSource {
    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinsResponseDto> {
            httpClient.get(
                urlString = constructUrl("/assets")
            )
        }.map { response ->
            response.data.map { it.toCoin() }
        }
    }

    override suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, NetworkError> {
        val startInMillis = start
            .withZoneSameLocal(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()
        val endInMillis = start
            .withZoneSameLocal(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()


        return safeCall<CoinHistoryDto> {
            httpClient.get(
                urlString = constructUrl("/assets/$coinId/history")
            ) {
                parameter("interval", "h6")
//                parameter("start", startInMillis)
//                parameter("end", endInMillis)
            }
        }.map { response ->
            response.data.map { it.toCoinPrice() }
        }
    }
}