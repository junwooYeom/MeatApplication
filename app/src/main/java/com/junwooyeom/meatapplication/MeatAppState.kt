package com.junwooyeom.meatapplication

import android.content.res.Resources
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.junwooyeom.meatapplication.presentation.view.HomeSections
import kotlinx.coroutines.CoroutineScope

object MainDestinations {
    const val HOME_ROUTE = "home"
    const val PRODUCT_DETAIL_ROUTE = "product"
    const val PRODUCT_KEY = "productKey"
}

@Composable
fun rememberMeatApplicationState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) = remember(scaffoldState, navController, resources, coroutineScope) {
    MeatAppState(scaffoldState, navController, resources, coroutineScope)
}

@Stable
class MeatAppState(
    val scaffoldState: ScaffoldState,
    val navController: NavHostController,
    private val resources: Resources,
    coroutineScope: CoroutineScope
) {

    val tabBars = HomeSections.values()
    private val tabBarRoutes = tabBars.map { it.route }

    val shouldShowTabs: Boolean
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination?.route in tabBarRoutes

    val currentRoute: String?
    get() = navController.currentDestination?.route

    fun pressUp() {
        navController.navigateUp()
    }

    fun navigateToTabRoute(route: String) {
        if (route != currentRoute) {
            navController.navigate(route) {
                launchSingleTop = true
                restoreState = true
                popUpTo(findStartDestination(navController.graph).id) {
                    saveState = true
                }
            }
        }
    }

    fun navigateToSnackDetail(product: String, from: NavBackStackEntry) {
        if (from.isLifecycleResumed()) {
            navController.navigate("${MainDestinations.PRODUCT_DETAIL_ROUTE}/$product")
        }
    }
}

private fun NavBackStackEntry.isLifecycleResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED

private val NavGraph.startDestination: NavDestination?
    get() = findNode(startDestinationId)

private tailrec fun findStartDestination(graph: NavDestination): NavDestination {
    return if (graph is NavGraph) findStartDestination(graph.startDestination!!) else graph
}

@Composable
@ReadOnlyComposable
private fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}
