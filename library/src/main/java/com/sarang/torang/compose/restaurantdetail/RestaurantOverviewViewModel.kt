package com.sarang.torang.compose.restaurantdetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantOverviewViewModel @Inject constructor() : ViewModel() {
    var isLoading : Boolean by mutableStateOf(false); private set

    fun onRefresh() {
        viewModelScope.launch {
            isLoading = true
            delay(1000)
            isLoading = false
        }
    }
}