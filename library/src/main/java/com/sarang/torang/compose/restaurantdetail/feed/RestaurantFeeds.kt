package com.sarang.torang.compose.restaurantdetail.feed

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RestaurantFeeds(
    restaurantId: Int,
    viewModel: RestaurantFeedsViewModel = hiltViewModel(),
    onErrorMessage : (String) -> Unit = {}
) {
    val uiState = viewModel.uiState
    val isLogin by viewModel.isLogin.collectAsStateWithLifecycle()

    LaunchedEffect(restaurantId) {
        viewModel.fetch(restaurantId)
    }

    LaunchedEffect(Unit) {
        viewModel.errorMessage.collect {
            if(it.isNotEmpty()) {
                onErrorMessage.invoke(it[0])
                viewModel.onErrorMessage()
            }
        }
    }

    Column {
        for (i in 0 until uiState.size) {
            LocalRestaurantFeed.current.invoke(
                RestaurantFeedData(
                    feed = uiState[i],
                    onLike = viewModel::onLike,
                    onFavorite = viewModel::onFavorite,
                    isLogin = isLogin,
                    onVideoClick = {},
                    pageScrollAble = true
                )
            )
        }
    }
}