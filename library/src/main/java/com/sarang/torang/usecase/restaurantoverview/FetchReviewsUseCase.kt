package com.sarang.torang.usecase.restaurantoverview

import com.sarang.torang.data.FeedInRestaurant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface FetchReviewsUseCase {
    suspend fun invoke(restaurantId: Int) : Flow<List<FeedInRestaurant>>
}