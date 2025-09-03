package com.sarang.torang.compose.restaurantdetail.feed

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import com.sarang.torang.data.FeedInRestaurant

typealias RestaurantFeedType = @Composable (
    feed: FeedInRestaurant,
    onLike: (Int) -> Unit,
    onFavorite: (Int) -> Unit,
    isLogin: Boolean,
    onVideoClick: () -> Unit,
    imageHeight: Int,
    pageScrollAble: Boolean
) -> Unit

val LocalRestaurantFeed = compositionLocalOf<RestaurantFeedType> {
    @Composable { feed, _, _, _, _, _, _ ->
        Log.w("__LocalFeedCompose", "feed compose isn't set")
        Row {
            Text(feed.name)
            Text(feed.restaurantName)
        }
    }
}