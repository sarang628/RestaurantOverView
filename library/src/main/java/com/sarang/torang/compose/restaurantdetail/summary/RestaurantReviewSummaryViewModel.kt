package com.sarang.library.compose.restaurantdetail.summary

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarang.library.data.ReviewSummaryData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantReviewSummaryViewModel @Inject constructor(

) : ViewModel() {
    val tag = "__RestaurantReviewSummaryViewModel"
    var uiState: ReviewSummaryData by mutableStateOf(ReviewSummaryData())
        private set

    fun fetch(restaurantId: Int) {
        viewModelScope.launch {
            try {
                uiState = ReviewSummaryData()
            } catch (e: Exception) {
                Log.e(tag, "$e")
            }
        }
    }
}