package com.sarang.torang.usecase.restaurantoverview

import com.sarang.torang.data.FeedInRestaurant

interface FetchReviewsUseCase {
    suspend fun invoke(restaurantId: Int) : List<FeedInRestaurant>
}