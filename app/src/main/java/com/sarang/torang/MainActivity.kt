package com.sarang.torang

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sarang.library.compose.DetailRestaurantInfo
import com.sarang.library.compose.LocalDetailRestaurantInfo
import com.sarang.library.compose.LocalRestaurantDetailImageLoader
import com.sarang.library.compose.RestaurantDetailImageLoader
import com.sarang.library.compose.restaurantdetail.RestaurantDetailScreen
import com.sarang.torang.di.image.provideTorangAsyncImage
import com.sarang.torang.di.restaurant_info.RestaurantInfoWithPermission
import com.sarang.torang.ui.theme.RestaurantOverViewTheme
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
                    LocalRestaurantDetailImageLoader provides restaurantDetailImageLoader,
                    LocalDetailRestaurantInfo provides customRestaurantInfo,
                    LocalRestaurantInfoImageLoader provides restaurantImageLoader
                ) {
                    RestaurantDetailScreen(restaurantId = 234)
                }
            }
        }
    }
}

val restaurantDetailImageLoader: RestaurantDetailImageLoader = { modifier, url, width, height, scale ->
    // 여기서 실제 이미지 로딩 구현 예시
    provideTorangAsyncImage().invoke(modifier, url, width, height, scale)
}

val customRestaurantInfo: DetailRestaurantInfo = {
    RestaurantInfoWithPermission(restaurantId = it, viewModel = BestPracticeViewModel())
}

val restaurantImageLoader: RestaurantInfoImageLoader = { modifier, url, width, height, scale ->
    // 여기서 실제 이미지 로딩 구현 예시
    provideTorangAsyncImage().invoke(modifier, url, width, height, scale)
}