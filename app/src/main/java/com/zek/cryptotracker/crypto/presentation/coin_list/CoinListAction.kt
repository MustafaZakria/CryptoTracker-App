package com.zek.cryptotracker.crypto.presentation.coin_list

import com.zek.cryptotracker.crypto.presentation.models.CoinUi

sealed interface CoinListAction {
    data class onCoinClick(val coinUi: CoinUi): CoinListAction
}