package com.sarang.torang.usecase.restaurantoverview

import com.sarang.library.data.MenuData

interface GetMenuUseCase {
    suspend fun invoke(restaurantId: Int): List<MenuData>
}