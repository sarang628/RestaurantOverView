package com.sarang.torang.compose.type

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf

typealias RestaurantOverviewRestaurantInfo = @Composable (
    restaurantId: Int
) -> Unit


val LocalRestaurantOverviewRestaurantInfo = compositionLocalOf<RestaurantOverviewRestaurantInfo> {
    @Composable {
        Log.w("__RestaurantOverviewRestaurantInfo", "no RestaurantInfo compose")
    }
}