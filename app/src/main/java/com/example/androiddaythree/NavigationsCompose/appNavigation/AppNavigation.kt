package com.example.androiddaythree.NavigationsCompose.appNavigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

// ១. កំណត់ Route សម្រាប់ HomeScreen និង DetailScreen ដោយប្រើ @Serializable
@Serializable
object HomeScreenRoute

@Serializable
data class DetailScreenRoute(val itemId: String)

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HomeScreenRoute // កំណត់ Start Destination ឱ្យចំ Route
    ) {
        // អេក្រង់ទី ១: Home Screen
        composable<HomeScreenRoute> {
            HomeScreen(
                onNavigateToDetail = { id ->
                    navController.navigate(DetailScreenRoute(itemId = id))
                }
            )
        }

        // អេក្រង់ទី ២: Detail Screen
        composable<DetailScreenRoute> { backStackEntry ->
            val detail: DetailScreenRoute = backStackEntry.toRoute()
            DetailScreen(
                itemId = detail.itemId,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

@Composable
fun HomeScreen(onNavigateToDetail: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Home Screen")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onNavigateToDetail("Item-123") }) {
            Text(text = "Go to Details")
        }
    }
}

@Composable
fun DetailScreen(itemId: String, onBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Detail Screen")
        Text(text = "Item ID: $itemId")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onBack) {
            Text(text = "Back")
        }
    }
}
