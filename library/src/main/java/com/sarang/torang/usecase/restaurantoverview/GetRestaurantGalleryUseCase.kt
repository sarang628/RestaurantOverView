package com.sarang.torang.usecase.restaurantoverview

import com.sarang.library.data.RestaurantImage

interface GetRestaurantGalleryUseCase {
    suspend fun invoke(restaurantId : Int): List<RestaurantImage>
}