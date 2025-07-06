package com.sarang.library.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf


typealias PullToRefresh = @Composable (
    isRefreshing: Boolean,
    onRefresh: (() -> Unit),
    contents: @Composable () -> Unit
) -> Unit

val LocalPullToRefresh = compositionLocalOf<PullToRefresh> {
    // 기본 구현: 경고 로그 출력
    @Composable { isRefreshing, onRefresh, contents ->
        contents.invoke()
    }
}