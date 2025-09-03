package com.sarang.library.usecase

import com.sarang.torang.data.FeedInRestaurant

interface FetchReviewsUseCase {
    suspend fun invoke(restaurantId: Int) : List<FeedInRestaurant>
}