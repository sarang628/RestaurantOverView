package com.sarang.library.compose.restaurantdetail

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.sarang.library.compose.LocalDetailRestaurantInfo
import com.sarang.library.compose.LocalPullToRefresh
import com.sarang.library.compose.RestaurantInfoTitle
import com.sarang.library.compose.restaurantdetail.feed.RestaurantFeeds
import com.sarang.library.compose.restaurantdetail.gallery.RestaurantImages
import com.sarang.library.compose.restaurantdetail.menu.RestaurantMenus
import com.sarang.library.compose.restaurantdetail.review.RestaurantReviews
import com.sarang.library.compose.restaurantdetail.summary.RestaurantReviewSummary
import com.sarang.library.data.Feed
import com.sarang.torang.compose.restaurant.detail.components.RestaurantReservation

enum class RestaurantDetailOrder{
    RestaurantInfo,
    RestaurantReservation,
    RestaurantImages,
    RestaurantMenu,
    RestaurantMenus,
    RestaurantReviewSummary,
    RestaurantReviews,
    Feed
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantDetailScreen(
    tag: String = "__RestaurantInfoScreen",
    restaurantId : Int,
    modifier: Modifier = Modifier,
    onRefresh: () -> Unit = { Log.w(tag, "onRefresh is null") },
    onLocation: () -> Unit = { Log.w(tag, "onLocation is null") },
    onWeb: (String) -> Unit = { Log.w(tag, "onWeb is null") },
    onCall: (String) -> Unit = { Log.w(tag, "onCall is null") },
    onImage: (Int) -> Unit = { Log.w(tag, "onImage is null") },
    progressTintColor: Color? = null,
    onProfile: (Int) -> Unit = { Log.w(tag, "onProfile is null") },
    onContents: (Int) -> Unit = { Log.w(tag, "onContents is null") },
    scrollBehavior: TopAppBarScrollBehavior? = null,
    feed: @Composable (Feed) -> Unit = { Log.w(tag, "feed is null") },
) {

    LocalPullToRefresh.current.invoke(false, { onRefresh.invoke() }) {
        LazyColumn(
            modifier = if (scrollBehavior != null) modifier.nestedScroll(scrollBehavior.nestedScrollConnection) else Modifier
        )
        {
            items(RestaurantDetailOrder.values().size) {
                when(RestaurantDetailOrder.values()[it]){
                    RestaurantDetailOrder.RestaurantInfo -> { // 레스토랑 기본정보
                        LocalDetailRestaurantInfo.current.invoke(restaurantId)
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    RestaurantDetailOrder.RestaurantImages -> { // 이미지
                        Box(Modifier.padding(start = 8.dp, end = 8.dp)) { RestaurantInfoTitle(title = "Images") }
                        RestaurantImages(restaurantId = restaurantId, onImage = onImage)
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    RestaurantDetailOrder.RestaurantMenu -> { // 메뉴
                        Column(Modifier.padding(start = 8.dp, end = 8.dp)) {
                            RestaurantInfoTitle(title = "Menus")
                        }
                    }

                    RestaurantDetailOrder.RestaurantMenus -> { // 메뉴
                        RestaurantMenus(restaurantId = restaurantId); Spacer(modifier = Modifier.height(16.dp))
                    }

                    RestaurantDetailOrder.RestaurantReviewSummary -> { // 리뷰 통계
                        RestaurantReviewSummary(restaurantId = restaurantId, progressTintColor = progressTintColor)
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    RestaurantDetailOrder.RestaurantReviews -> { // 리뷰
                        RestaurantReviews(restaurantId = restaurantId, progressTintColor = progressTintColor, onProfile = onProfile, onContents = onContents)
                    }

                    RestaurantDetailOrder.Feed -> { // 피드
                        Box(Modifier.padding(start = 8.dp, end = 8.dp)) { RestaurantInfoTitle(title = "Reviews") }
                        RestaurantFeeds(restaurantId = restaurantId, feed = feed)
                    }

                    RestaurantDetailOrder.RestaurantReservation -> {
                        RestaurantReservation()
                    }
                }
            }
        }
    }
}