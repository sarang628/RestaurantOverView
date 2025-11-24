package com.sarang.torang.usecase.restaurantoverview

import kotlinx.coroutines.flow.Flow

interface IsLoginUseCase {
    fun invoke() : Flow<Boolean>
}