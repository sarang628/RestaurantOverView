package com.sarang.torang.compose.restaurantdetail.feed

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun RestaurantFeeds(
    restaurantId: Int,
    viewModel: RestaurantFeedsViewModel = hiltViewModel(),
) {

    val uiState = viewModel.uiState

    LaunchedEffect(restaurantId) {
        viewModel.fetch(restaurantId)
    }

    Column {
            for (i in 0 until uiState.size) {
                LocalRestaurantFeed.current.invoke(uiState[i], {}, {}, false, {}, 300, true)
            }
    }
}