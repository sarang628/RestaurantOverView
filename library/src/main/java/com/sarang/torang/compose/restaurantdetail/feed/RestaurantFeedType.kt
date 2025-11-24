package com.sarang.torang.compose.restaurantdetail.feed

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import com.sarang.torang.data.FeedInRestaurant

typealias RestaurantFeedType = @Composable (RestaurantFeedData) -> Unit

data class RestaurantFeedData(
    val feed: FeedInRestaurant,
    val onLike: (Int) -> Unit,
    val onFavorite: (Int) -> Unit,
    val isLogin: Boolean,
    val onVideoClick: () -> Unit,
    val pageScrollAble: Boolean
)

val LocalRestaurantFeed = compositionLocalOf<RestaurantFeedType> {
    @Composable {
        Log.w("__LocalFeedCompose", "feed compose isn't set")
        Row {
            Text(it.feed.name)
            Text(it.feed.restaurantName)
        }
    }
}