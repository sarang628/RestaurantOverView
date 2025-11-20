package com.sarang.torang.usecase.restaurantoverview

interface OnLikeUseCase {
    suspend fun invoke(reviewId : Int)
}