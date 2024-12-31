package com.zek.cryptotracker.crypto.domain

import com.zek.cryptotracker.core.domain.util.NetworkError
import com.zek.cryptotracker.core.domain.util.Result

interface CoinDataSource {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
}