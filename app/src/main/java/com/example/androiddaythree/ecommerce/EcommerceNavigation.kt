package com.example.androiddaythree.ecommerce

import androidx.compose.animation.*
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.androiddaythree.ecommerce.ui.screens.CartScreen
import com.example.androiddaythree.ecommerce.ui.screens.DetailScreen
import com.example.androiddaythree.ecommerce.ui.screens.HomeScreen
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

@Serializable
data class DetailRoute(val productId: Int)

@Serializable
object CartRoute

@Serializable
object FavoriteRoute

@Serializable
object ProfileRoute

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun EcommerceApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val showBottomBar = currentDestination?.hierarchy?.any { 
        it.hasRoute<HomeRoute>() || it.hasRoute<CartRoute>() || it.hasRoute<FavoriteRoute>() || it.hasRoute<ProfileRoute>()
    } == true

    SharedTransitionLayout {
        Scaffold(
            bottomBar = {
                if (showBottomBar) {
                    NavigationBar(containerColor = Color.White) {
                        NavigationBarItem(
                            selected = currentDestination.hierarchy.any { it.hasRoute<HomeRoute>() } == true,
                            onClick = { 
                                navController.navigate(HomeRoute) {
                                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = { Icon(Icons.Default.Home, null) }
                        )
                        NavigationBarItem(
                            selected = currentDestination.hierarchy.any { it.hasRoute<CartRoute>() } == true,
                            onClick = { navController.navigate(CartRoute) },
                            icon = { Icon(Icons.Default.ShoppingCart, null) }
                        )
                        NavigationBarItem(
                            selected = currentDestination.hierarchy.any { it.hasRoute<FavoriteRoute>() } == true,
                            onClick = { },
                            icon = { Icon(Icons.Default.FavoriteBorder, null) }
                        )
                        NavigationBarItem(
                            selected = currentDestination.hierarchy.any { it.hasRoute<ProfileRoute>() } == true,
                            onClick = { },
                            icon = { Icon(Icons.Default.Person, null) }
                        )
                    }
                }
            }
        ) { padding ->
            NavHost(
                navController = navController,
                startDestination = HomeRoute,
                modifier = Modifier.padding(padding)
            ) {
                composable<HomeRoute> {
                    HomeScreen(
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedContentScope = this@composable,
                        onProductClick = { id ->
                            navController.navigate(DetailRoute(id))
                        }
                    )
                }

                composable<DetailRoute> { backStackEntry ->
                    val route: DetailRoute = backStackEntry.toRoute()
                    DetailScreen(
                        productId = route.productId,
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedContentScope = this@composable,
                        onBack = { navController.popBackStack() }
                    )
                }

                composable<CartRoute> {
                    CartScreen(onBack = { navController.popBackStack() })
                }
            }
        }
    }
}
