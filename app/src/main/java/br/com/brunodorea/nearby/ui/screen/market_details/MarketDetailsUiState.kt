package br.com.brunodorea.nearby.ui.screen.market_details

import br.com.brunodorea.nearby.data.model.Rule

data class MarketDetailsUiState(
    val rules: List<Rule>? = null,
    val coupon: String? = null,
)
