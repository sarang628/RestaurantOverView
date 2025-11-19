package com.sarang.torang

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
import com.sarang.torang.repository.FindRepository
import com.sryang.torang.ui.TorangTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var findRepository : FindRepository

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TorangTheme {
                val viewModel : RestaurantInfoViewModel = hiltViewModel()
                val restaurants by findRepository.restaurants.collectAsStateWithLifecycle()
                val scaffoldState = rememberBottomSheetScaffoldState()
                val scope = rememberCoroutineScope()
                var restaurantId by remember { mutableStateOf(0) }
                CompositionLocalProvider(
                    LocalRestaurantOverViewImageLoader    provides restaurantOverViewImageLoader,
                    LocalRestaurantOverviewRestaurantInfo provides restaurantOverViewRestaurantInfo(
                        rootNavController = RootNavController(), viewModel = viewModel),
                    LocalRestaurantFeed                   provides CustomRestaurantFeedType,
                    LocalExpandableTextType               provides CustomExpandableTextType,
                    LocalFeedImageLoader                  provides CustomFeedImageLoader(),
                    LocalPullToRefresh                    provides CustomRestaurantOverviewPullToRefreshType
                ) {
                    BottomSheetScaffold(
                        scaffoldState = scaffoldState,
                        sheetPeekHeight = 0.dp,
                        sheetContent = {
                            LazyColumn(Modifier.fillMaxSize()) {
                                items(restaurants.reversed()){
                                    TextButton({
                                        restaurantId = it.restaurant.restaurantId
                                        scope.launch {
                                            scaffoldState.bottomSheetState.partialExpand()
                                        }
                                    }) {
                                        Text(it.restaurant.restaurantName)
                                    }
                                }
                            }
                        }
                    ) {
                        Box(Modifier.fillMaxSize()){
                            RestaurantOverViewScreen(restaurantId = restaurantId, onRefresh = {viewModel.refresh(1)}, isRefreshRestaurantInfo = viewModel.isRefresh)
                            FloatingActionButton(
                                modifier = Modifier.align(Alignment.BottomEnd)
                                    .padding(bottom = 24.dp, end = 12.dp),
                                onClick = {
                                scope.launch {
                                    scope.launch {
                                        findRepository.findFilter()
                                    }
                                    scaffoldState.bottomSheetState.expand()
                                }
                            }) {
                                Icon(Icons.AutoMirrored.Default.List, null)
                            }
                        }
                    }
                }
            }
        }
    }
}

