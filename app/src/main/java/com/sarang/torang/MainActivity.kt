package com.sarang.torang

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.CompositionLocalProvider
import com.sarang.torang.compose.type.RestaurantOverviewRestaurantInfo
import com.sarang.torang.compose.type.LocalDetailRestaurantInfo
import com.sarang.torang.compose.type.LocalRestaurantOverViewImageLoader
import com.sarang.torang.compose.type.RestaurantOverViewImageLoader
import com.sarang.torang.compose.restaurantdetail.RestaurantOverViewScreen
import com.sarang.torang.di.image.provideTorangAsyncImage
import com.sarang.torang.di.restaurant_info.RestaurantInfoWithPermission
import com.sryang.library.compose.workflow.BestPracticeViewModel
import com.sryang.torang.ui.TorangTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TorangTheme {
                CompositionLocalProvider(
                    LocalRestaurantOverViewImageLoader provides restaurantOverViewImageLoader,
                    LocalDetailRestaurantInfo provides restaurantOverViewRestaurantInfo,
                    LocalRestaurantInfoImageLoader provides restaurantImageLoader
                ) {
                    RestaurantOverViewScreen(restaurantId = 234)
                }
            }
        }
    }
}

val restaurantOverViewImageLoader: RestaurantOverViewImageLoader = { modifier, url, width, height, scale ->
    // 여기서 실제 이미지 로딩 구현 예시
    provideTorangAsyncImage().invoke(modifier, url, width, height, scale)
}

val restaurantOverViewRestaurantInfo: RestaurantOverviewRestaurantInfo = {
    RestaurantInfoWithPermission(restaurantId = it, viewModel = BestPracticeViewModel())
}

val restaurantImageLoader: RestaurantInfoImageLoader = { modifier, url, width, height, scale ->
    // 여기서 실제 이미지 로딩 구현 예시
    provideTorangAsyncImage().invoke(modifier, url, width, height, scale)
}