package com.sarang.library

import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sarang.library.compose.restaurantdetail.RestaurantDetailScreen
import com.sarang.library.data.Feed

/**
 * @param map map compose
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantDetailNavigationScreen(
    tag : String = "__RestaurantDetailNavigationScreen",
    restaurantId: Int,
    progressTintColor: Color? = null,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    onWeb: (String) -> Unit = { Log.w(tag, "onWeb is null") },
    onCall: (String) -> Unit = { Log.w(tag, "onCall is null") },
    onImage: (Int) -> Unit = { Log.w(tag, "onImage is null") },
    onProfile: (Int) -> Unit = { Log.w(tag, "onProfile is null") },
    onContents: (Int) -> Unit = { Log.w(tag, "onContents is null") },
    map: @Composable (restaurantName: String, latitude: Double, longitude: Double, foodType: String) -> Unit = { _, _, _, _ -> Log.w(tag, "map is null") },
    onError: (String) -> Unit = { Log.w(tag, "feed doesn't set") },
    feed: @Composable (Feed) -> Unit = { Log.w(tag, "feed doesn't set") },
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "info") {
        composable("info") {
            RestaurantDetailScreen(
                restaurantId = restaurantId,
                onCall = onCall,
                onWeb = onWeb,
                onLocation = { navController.navigate("map") },
                onImage = onImage,
                scrollBehavior = scrollBehavior,
                progressTintColor = progressTintColor,
                onContents = onContents,
                onProfile = onProfile,
                feed = feed,
            )
        }
        composable("map") {
            map.invoke("restaurantName", 0.0, 0.0, "")
        }
    }
}