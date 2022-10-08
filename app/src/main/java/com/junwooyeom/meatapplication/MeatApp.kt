package com.junwooyeom.meatapplication

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.junwooyeom.meatapplication.presentation.view.ProductDetail
import com.junwooyeom.meatapplication.presentation.component.MeatTopTabs
import com.junwooyeom.meatapplication.presentation.view.HomeSections
import com.junwooyeom.meatapplication.presentation.view.addHomeGraph
import com.junwooyeom.meatapplication.ui.theme.MeatApplicationTheme

@Composable
fun MeatApp() {
    MeatApplicationTheme {
        val appState = rememberMeatApplicationState()
        MeatScaffold(
            topBar = {
                if (appState.shouldShowTabs) {
                    MeatTopTabs(
                        tabs = appState.tabBars,
                        currentRoute = appState.currentRoute!!,
                        navigateToRoute = appState::navigateToTabRoute
                    )
                }
            },
            scaffoldState = appState.scaffoldState
        ) { innerPaddingModifier ->
            NavHost(
                navController = appState.navController,
                startDestination = MainDestinations.HOME_ROUTE,
                modifier = Modifier.padding(innerPaddingModifier)
            ) {
                meatNavGraph(
                    onProductSelected = appState::navigateToSnackDetail,
                    upPress = appState::pressUp
                )
            }
        }
    }
}

private fun NavGraphBuilder.meatNavGraph(
    onProductSelected: (String, NavBackStackEntry) -> Unit,
    upPress: () -> Unit
) {
    navigation(
        route = MainDestinations.HOME_ROUTE,
        startDestination = HomeSections.LIST.route
    ) {
        addHomeGraph(onProductSelected)
    }
    composable(
        "${MainDestinations.PRODUCT_DETAIL_ROUTE}/{${MainDestinations.PRODUCT_KEY}}",
        arguments = listOf(navArgument(MainDestinations.PRODUCT_KEY) { type = NavType.StringType })
    ) { backStackEntry ->
        val arguments = requireNotNull(backStackEntry.arguments)
        val productKey = arguments.getString(MainDestinations.PRODUCT_KEY) ?: ""
        ProductDetail(productKey, upPress)
    }
}
