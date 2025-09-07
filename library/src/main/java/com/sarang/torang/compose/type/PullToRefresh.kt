package com.sarang.torang.compose.type

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier


typealias PullToRefresh = @Composable (
    modifier : Modifier,
    isRefreshing: Boolean,
    onRefresh: (() -> Unit),
    contents: @Composable () -> Unit
) -> Unit

val LocalPullToRefresh = compositionLocalOf<PullToRefresh> {
    // 기본 구현: 경고 로그 출력
    @Composable { modifier, isRefreshing, onRefresh, contents ->
        contents.invoke()
    }
}