package com.sarang.torang

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.CompositionLocalProvider
import androidx.hilt.navigation.compose.hiltViewModel
import com.sarang.torang.compose.feed.internal.components.LocalExpandableTextType
import com.sarang.torang.compose.feed.internal.components.LocalFeedImageLoader
import com.sarang.torang.compose.restaurantdetail.RestaurantOverViewScreen
import com.sarang.torang.compose.restaurantdetail.feed.LocalRestaurantFeed
import com.sarang.torang.compose.type.LocalPullToRefresh
import com.sarang.torang.compose.type.LocalRestaurantOverViewImageLoader
import com.sarang.torang.compose.type.LocalRestaurantOverviewRestaurantInfo
import com.sarang.torang.di.basefeed_di.CustomExpandableTextType
import com.sarang.torang.di.basefeed_di.CustomFeedImageLoader
import com.sarang.torang.di.restaurant_overview_di.CustomRestaurantFeedType
import com.sarang.torang.di.restaurant_overview_di.CustomRestaurantOverviewPullToRefreshType
import com.sarang.torang.di.restaurant_overview_di.restaurantOverViewImageLoader
import com.sarang.torang.di.restaurant_overview_di.restaurantOverViewRestaurantInfo
import com.sryang.torang.ui.TorangTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TorangTheme {
                val viewModel : RestaurantInfoViewModel = hiltViewModel()
                CompositionLocalProvider(
                    LocalRestaurantOverViewImageLoader    provides restaurantOverViewImageLoader,
                    LocalRestaurantOverviewRestaurantInfo provides restaurantOverViewRestaurantInfo(RootNavController(), viewModel),
                    LocalRestaurantFeed                   provides CustomRestaurantFeedType,
                    LocalExpandableTextType               provides CustomExpandableTextType,
                    LocalFeedImageLoader                  provides CustomFeedImageLoader,
                    LocalPullToRefresh                    provides CustomRestaurantOverviewPullToRefreshType
                ) {
                    RestaurantOverViewScreen(restaurantId = 1, onRefresh = {viewModel.refresh(1)}, isRefreshRestaurantInfo = viewModel.isRefresh)
                }
            }
        }
    }
}

