package com.zek.cryptotracker.crypto.presentation.coin_list

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.zek.cryptotracker.core.presentation.util.ObserveAsEvents
import com.zek.cryptotracker.core.presentation.util.toString
import com.zek.cryptotracker.crypto.presentation.coin_list.components.CoinListItem
import com.zek.cryptotracker.crypto.presentation.coin_list.components.previewCoin
import com.zek.cryptotracker.crypto.presentation.models.CoinUi
import com.zek.cryptotracker.ui.theme.CryptoTrackerAppTheme
import kotlinx.coroutines.flow.Flow

@Composable
fun CoinListScreen(
    state: CoinListState,
    onActionClick: (CoinListAction) -> Unit,
    modifier: Modifier = Modifier
) {

    if (state.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.coins) { coinUi ->
                CoinListItem(
                    coinUi = coinUi,
                    onClick = { onActionClick.invoke(CoinListAction.onCoinClick(coinUi)) },
                    modifier = Modifier.fillMaxWidth()
                )
                HorizontalDivider()
            }
        }
    }
}

@PreviewLightDark
@Composable
fun CoinListPreview() {
    CryptoTrackerAppTheme {
        CoinListScreen(
            state = CoinListState(
                coins = (1..100).map {
                    previewCoin.copy(id = it.toString())
                }
            ),
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            onCoinClick = {}
        )
    }
}