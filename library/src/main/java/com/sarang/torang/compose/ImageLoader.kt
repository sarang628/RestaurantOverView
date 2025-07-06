package com.sarang.library.compose

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp

typealias RestaurantDetailImageLoader = @Composable (
    modifier: Modifier,
    url: String,
    width: Dp?,
    height: Dp?,
    contentScale: ContentScale?
) -> Unit

val LocalRestaurantDetailImageLoader = compositionLocalOf<RestaurantDetailImageLoader> {
    // 기본 구현: 경고 로그 출력
    @Composable { _, _, _, _, _ ->
        Log.w("__ImageLoader", "No ImageLoader provided.")
    }
}