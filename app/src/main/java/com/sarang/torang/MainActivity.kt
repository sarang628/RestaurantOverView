package com.sarang.torang

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import com.sarang.library.compose.restaurantdetail.gallery.RestaurantImages
import com.sarang.torang.compose.feed.internal.components.type.LocalExpandableTextType
import com.sarang.torang.compose.feed.internal.components.type.LocalFeedImageLoader
import com.sarang.torang.compose.restaurantdetail.RestaurantInfoTitle
import com.sarang.torang.compose.restaurantdetail.RestaurantReservation
import com.sarang.torang.compose.restaurantdetail.feed.LocalRestaurantFeed
import com.sarang.torang.compose.restaurantdetail.feed.RestaurantFeeds
import com.sarang.torang.compose.restaurantdetail.menu.RestaurantMenus
import com.sarang.torang.compose.restaurantdetail.summary.RestaurantReviewSummary
import com.sarang.torang.compose.type.LocalRestaurantOverViewImageLoader
import com.sarang.torang.di.basefeed_di.CustomExpandableTextType
import com.sarang.torang.di.basefeed_di.CustomFeedImageLoader
import com.sarang.torang.di.restaurant_overview_di.customRestaurantFeedType
import com.sarang.torang.di.restaurant_overview_di.restaurantOverViewImageLoader
import com.sarang.torang.di.restaurant_overview_di.restaurantOverViewRestaurantInfo
import com.sarang.torang.repository.FindRepository
import com.sarang.torang.repository.LoginRepository
import com.sarang.torang.repository.test.LoginRepositoryTest
import com.sryang.torang.ui.TorangTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var findRepository : FindRepository
    @Inject lateinit var loginRepository: LoginRepository

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel : RestaurantInfoViewModel = hiltViewModel()
            TorangTheme {
                OverViewTestContainer(findRepository = findRepository){restaurantId, state ->
                    RestaurantOverViewTestMenu(
                        loginRepositoryTest     = { LoginRepositoryTest(loginRepository) },
                        restaurantOverView      = { CompositionLocalProvider(LocalRestaurantOverViewImageLoader    provides restaurantOverViewImageLoader){
                                                    restaurantOverViewRestaurantInfo(rootNavController = RootNavController(),
                                                                                     viewModel = viewModel).invoke(restaurantId) }
                                                  },
                        restaurantImages         = { CompositionLocalProvider(LocalRestaurantOverViewImageLoader    provides restaurantOverViewImageLoader){
                                                     RestaurantImages(restaurantId = restaurantId) }
                                                   },
                        restaurantMenus          = { RestaurantMenus(restaurantId = restaurantId) },
                        restaurantReviewSummary  = { RestaurantReviewSummary(restaurantId = restaurantId) },
                        restaurantFeeds          = { CompositionLocalProvider(LocalRestaurantFeed        provides customRestaurantFeedType(rootNavController = RootNavController()),
                                                                             LocalExpandableTextType               provides CustomExpandableTextType,
                                                                             LocalFeedImageLoader                  provides CustomFeedImageLoader()){
                                                    RestaurantFeeds(restaurantId = restaurantId)}
                                                  },
                        restaurantReservation   = { RestaurantReservation()},
                        restaurantInfoTitle     = { RestaurantInfoTitle(title = "Reviews") }

                    )
                }
            }
        }
    }
}