package com.sarang.torang.usecase.restaurantoverview

interface GetAllRestaurantListUseCase {
    suspend fun invoke(): List<String>
}