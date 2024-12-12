package br.com.brunodorea.nearby.data.model

import androidx.annotation.DrawableRes
import br.com.brunodorea.nearby.ui.component.category.NearbyCategoryFilterChipView
import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val id: String,
    val name: String
) {
    @get:DrawableRes
    val icon: Int?
        get() = NearbyCategoryFilterChipView.Companion.fromDescription(description = name)?.icon
}
