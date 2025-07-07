package com.sarang.torang

import android.app.Application
import androidx.compose.runtime.CompositionLocalProvider
import com.sarang.torang.compose.type.RestaurantOverViewImageLoader
import com.sarang.torang.compose.type.RestaurantOverviewRestaurantInfo
import com.sarang.torang.di.image.provideTorangAsyncImage
import com.sarang.torang.di.restaurant_info.RestaurantInfoWithPermission
import com.sryang.library.compose.workflow.BestPracticeViewModel
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RestaurantOverViewApplication : Application()