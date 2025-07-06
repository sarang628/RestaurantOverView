package com.sarang.library.compose

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf

typealias DetailRestaurantInfo = @Composable (
    restaurantId: Int
) -> Unit


val LocalDetailRestaurantInfo = compositionLocalOf<DetailRestaurantInfo> {
    @Composable {
        Log.w("__RestaurantInfo", "no RestaurantInfo compose")
    }
}