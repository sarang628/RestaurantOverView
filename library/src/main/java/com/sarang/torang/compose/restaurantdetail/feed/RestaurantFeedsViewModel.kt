package com.sarang.torang.compose.restaurantdetail.feed

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarang.torang.data.FeedInRestaurant
import com.sarang.torang.usecase.restaurantoverview.FetchReviewsUseCase
import com.sarang.torang.usecase.restaurantoverview.ClickLikeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantFeedsViewModel @Inject constructor(
    val fetchReviewUseCase  : FetchReviewsUseCase,
    val clickLikeUseCase       : ClickLikeUseCase
) : ViewModel() {

    var uiState: List<FeedInRestaurant> by mutableStateOf(ArrayList())
    val tag = "__RestaurantFeedsViewModel"
    private var _errorMessage : MutableStateFlow<List<String>> = MutableStateFlow(listOf());
    var errorMessage : StateFlow<List<String>> = _errorMessage

    fun fetch(restaurantId: Int) {
        viewModelScope.launch {
            fetchReviewUseCase.invoke(restaurantId).collect {
                uiState = it
            }
        }
    }

    fun onLike(reviewId : Int){
        Log.d(tag, "onLike reviewId: $reviewId")
        viewModelScope.launch {
            try {
                clickLikeUseCase.invoke(reviewId)
            }catch (e : Exception){
                e.message?.let {
                    _errorMessage.value = errorMessage.value + it
                    Log.e(tag, it)
                }
            }
        }
    }

    fun onFavorite(reviewId : Int){
        Log.d(tag, "onFavorite reviewId: $reviewId")
    }

    fun onErrorMessage() {
        if(_errorMessage.value.isNotEmpty())
            _errorMessage.value = _errorMessage.value.dropLast(0)
    }
}