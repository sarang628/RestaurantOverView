package com.sarang.library.compose.restaurantdetail.review

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarang.library.data.ReviewRowData
import com.sarang.library.usecase.FetchReviewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantReviewViewModel @Inject constructor(
    private val fetchReviewUseCase: FetchReviewsUseCase,
) : ViewModel() {
    val tag = "__RestaurantReviewViewModel"
    var uiState: List<ReviewRowData> by mutableStateOf(listOf())
        private set

    fun fetch(restaurantId: Int) {
        viewModelScope.launch {
            try {
                uiState = fetchReviewUseCase.invoke(restaurantId).map {
                    ReviewRowData(
                        name = it.name,
                        fullName = it.name,
                        rating = it.rating,
                        comment = it.contents,
                        userId = it.userId,
                        reviewId = it.reviewId
                    )
                }
            }catch (e : Exception){
                Log.e(tag, "$e")
            }
        }
    }
}