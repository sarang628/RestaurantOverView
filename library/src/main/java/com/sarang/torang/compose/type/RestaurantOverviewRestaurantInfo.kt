package com.sarang.torang.compose.type

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf

typealias RestaurantOverviewRestaurantInfo = @Composable (
    restaurantId: Int
) -> Unit


val LocalDetailRestaurantInfo = compositionLocalOf<RestaurantOverviewRestaurantInfo> {
    @Composable {
        Log.w("__RestaurantInfo", "no RestaurantInfo compose")
    }
}