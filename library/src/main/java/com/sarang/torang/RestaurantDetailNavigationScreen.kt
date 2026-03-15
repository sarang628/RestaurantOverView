package com.sarang.torang

import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

/**
 * @param map map compose
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantDetailNavigationScreen(
    tag                 : String                    = "__RestaurantDetailNavigationScreen",
    restaurantId        : Int,
    progressTintColor   : Color?                    = null,
    onImage             : (Int) -> Unit             = { Log.w(tag, "onImage is null") },
    map                 : @Composable (restaurantName: String, latitude: Double, longitude: Double, foodType: String) -> Unit = { _, _, _, _ -> Log.w(tag, "map is null") }
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "info") {
        composable("info") {

        }
        composable("map") {
            map.invoke("restaurantName", 0.0, 0.0, "")
        }
    }
}