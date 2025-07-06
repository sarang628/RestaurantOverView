package com.sarang.library.usecase

import com.sarang.library.data.RestaurantImage

interface GetRestaurantGalleryUseCase {
    suspend fun invoke(restaurantId : Int): List<RestaurantImage>
}