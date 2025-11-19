package com.sarang.torang.compose.restaurantdetail

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sarang.torang.compose.type.LocalRestaurantOverviewRestaurantInfo
import com.sarang.torang.compose.type.LocalPullToRefresh
import com.sarang.torang.compose.restaurantdetail.feed.RestaurantFeeds
import com.sarang.library.compose.restaurantdetail.gallery.RestaurantImages
import com.sarang.torang.compose.restaurantdetail.menu.RestaurantMenus
import com.sarang.torang.compose.restaurantdetail.summary.RestaurantReviewSummary

enum class RestaurantDetailOrder {
    RestaurantInfo,
    RestaurantReservation,
    RestaurantImages,
    RestaurantMenus,
    RestaurantReviewSummary,
    Feed
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantOverViewScreen(
    modifier                : Modifier                      = Modifier,
    tag                     : String                        = "__RestaurantOverViewScreen",
    viewModel               : RestaurantOverviewViewModel   = hiltViewModel(),
    restaurantId            : Int,
    onRefresh               : () -> Unit                    = { Log.w(tag, "onRefresh is null") },
    onLocation              : () -> Unit                    = { Log.w(tag, "onLocation is null") },
    onWeb                   : (String) -> Unit              = { Log.w(tag, "onWeb is null") },
    onCall                  : (String) -> Unit              = { Log.w(tag, "onCall is null") },
    onImage                 : (Int) -> Unit                 = { Log.w(tag, "onImage is null") },
    progressTintColor       : Color?                        = null,
    onProfile               : (Int) -> Unit                 = { Log.w(tag, "onProfile is null") },
    onContents              : (Int) -> Unit                 = { Log.w(tag, "onContents is null") },
    scrollBehavior          : TopAppBarScrollBehavior?      = null,
    isRefreshRestaurantInfo : Boolean                       = false
) {

    LocalPullToRefresh.current.invoke(Modifier, isRefreshRestaurantInfo, { onRefresh.invoke() }) {
        LazyColumn(
            modifier = if (scrollBehavior != null) modifier.nestedScroll(scrollBehavior.nestedScrollConnection) else Modifier
        )
        {
            items(RestaurantDetailOrder.entries.size) {
                when(RestaurantDetailOrder.entries[it]){
                    RestaurantDetailOrder.RestaurantInfo -> { // 레스토랑 기본정보
                        LocalRestaurantOverviewRestaurantInfo.current.invoke(restaurantId)
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    RestaurantDetailOrder.RestaurantImages -> { // 이미지
                        Box(Modifier.padding(start = 8.dp, end = 8.dp)) { RestaurantInfoTitle(title = "Images") }
                        RestaurantImages(restaurantId = restaurantId, onImage = onImage)
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    RestaurantDetailOrder.RestaurantMenus -> { // 메뉴
                        RestaurantMenus(restaurantId = restaurantId); Spacer(modifier = Modifier.height(16.dp))
                    }

                    RestaurantDetailOrder.RestaurantReviewSummary -> { // 리뷰 통계
                        RestaurantReviewSummary(restaurantId = restaurantId, progressTintColor = progressTintColor)
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    RestaurantDetailOrder.Feed -> { // 피드
                        Box(Modifier.padding(start = 8.dp, end = 8.dp)) { RestaurantInfoTitle(title = "Reviews") }
                        Spacer(Modifier.height(8.dp))
                        RestaurantFeeds(restaurantId = restaurantId)
                    }

                    RestaurantDetailOrder.RestaurantReservation -> {
                        RestaurantReservation()
                    }
                }
            }
        }
    }
}