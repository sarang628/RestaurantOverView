package com.sarang.torang.usecase.restaurantoverview

interface ClickLikeUseCase {
    suspend fun invoke(reviewId : Int)
}