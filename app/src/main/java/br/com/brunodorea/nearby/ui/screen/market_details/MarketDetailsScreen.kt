package br.com.brunodorea.nearby.ui.screen.market_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.brunodorea.nearby.R
import br.com.brunodorea.nearby.data.model.Market
import br.com.brunodorea.nearby.data.model.mock.mockMarkets
import br.com.brunodorea.nearby.ui.component.button.NearbyButton
import br.com.brunodorea.nearby.ui.component.market_details.NearbyMarketDetailsCoupons
import br.com.brunodorea.nearby.ui.component.market_details.NearbyMarketDetailsInfos
import br.com.brunodorea.nearby.ui.component.market_details.NearbyMarketDetailsRules
import br.com.brunodorea.nearby.ui.theme.Typography
import coil3.compose.AsyncImage

@Composable
fun MarketDetailsScreen(
    modifier: Modifier = Modifier,
    uiState: MarketDetailsUiState,
    onEvent: (MarketDetailsUiEvent) -> Unit,
    market: Market,
    onNavigateToQRCodeScanner: () -> Unit,
    onNavigateBack: () -> Unit
) {
    LaunchedEffect(true) {
        onEvent(MarketDetailsUiEvent.OnFetchRules(marketId = market.id))
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f),
            contentDescription = "Imagem do Local",
            contentScale = ContentScale.Crop,
            model = market.cover
        )
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp))
                .fillMaxWidth()
                .fillMaxHeight(0.75f)
                .align(Alignment.BottomCenter)
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(16.dp)
            ) {
                Column {
                    Text(text = market.name, style = Typography.headlineLarge)
                    Spacer(modifier = Modifier.height(36.dp))
                    Text(text = market.description, style = Typography.bodyLarge)
                }
                Spacer(modifier = Modifier.height(48.dp))
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                ) {

                    NearbyMarketDetailsInfos(market = market)
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                    )
                    if (!uiState.rules.isNullOrEmpty()) {
                        NearbyMarketDetailsRules(rules = uiState.rules)
                        HorizontalDivider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp)
                        )
                    }
                    if (!uiState.coupon.isNullOrEmpty())
                        NearbyMarketDetailsCoupons(coupons = listOf(uiState.coupon))
                }

                NearbyButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    text = "Ler QR Code",
                    onClick = onNavigateToQRCodeScanner
                )
            }
        }
        NearbyButton(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(24.dp),
            iconRes = R.drawable.ic_arrow_left,
            onClick = onNavigateBack
        )
    }
}

@Preview
@Composable
private fun MarketDetailsScreenPreview() {
    MarketDetailsScreen(
        market = mockMarkets.first(),
        uiState = MarketDetailsUiState(),
        onEvent = {},
        onNavigateBack = {},
        onNavigateToQRCodeScanner = {}
    )
}