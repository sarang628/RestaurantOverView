package com.sarang.library.compose.restaurantdetail.feed

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.sarang.library.data.Feed
import com.sarang.library.compose.restaurantdetail.feed.RestaurantFeedsViewModel

@Composable
fun RestaurantFeeds(
    restaurantId: Int,
    viewModel: RestaurantFeedsViewModel = hiltViewModel(),
    feed: @Composable (Feed) -> Unit = {}
) {

    val uiState = viewModel.uiState

    LaunchedEffect(restaurantId) {
        viewModel.fetch(restaurantId)
    }

    Column {
        //if (uiState.isNotEmpty()) {
            for (i in 0 until uiState.size) {
                feed.invoke(uiState[i])
            }
        //}
    }
}