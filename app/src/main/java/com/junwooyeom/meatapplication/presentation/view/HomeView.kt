package com.junwooyeom.meatapplication.presentation.view

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.List
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.junwooyeom.meatapplication.R

fun NavGraphBuilder.addHomeGraph(
    onProductSelected: (String, NavBackStackEntry) -> Unit,
    modifier: Modifier = Modifier
) {
    composable(HomeSections.LIST.route) { from ->
        ProductList(
            onProductClick = { product -> onProductSelected(product.key, from) },
            modifier = modifier)
    }
    composable(HomeSections.FAVORITE.route) { from ->
        FavoriteList(
            onProductClick = { product -> onProductSelected(product.key, from) },
            modifier = modifier,
        )
    }
}

enum class HomeSections(
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String
) {
    LIST(R.string.home_category_list, Icons.Outlined.List, "home/list"),
    FAVORITE(R.string.home_category_favorite, Icons.Outlined.Favorite, "home/favorite")
}
