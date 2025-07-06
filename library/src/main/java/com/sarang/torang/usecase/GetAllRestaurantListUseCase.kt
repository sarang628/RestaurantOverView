package com.sarang.library.usecase

interface GetAllRestaurantListUseCase {
    suspend fun invoke(): List<String>
}