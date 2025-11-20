package com.sarang.torang.compose.restaurantdetail.feed

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarang.torang.data.FeedInRestaurant
import com.sarang.torang.usecase.restaurantoverview.FetchReviewsUseCase
import com.sarang.torang.usecase.restaurantoverview.OnLikeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.Stack
import javax.inject.Inject

@HiltViewModel
class RestaurantFeedsViewModel @Inject constructor(
    val fetchReviewUseCase  : FetchReviewsUseCase,
    val onLikeUseCase       : OnLikeUseCase
) : ViewModel() {

    var uiState: List<FeedInRestaurant> by mutableStateOf(ArrayList())
    val tag = "__RestaurantFeedsViewModel"
    var errorMessage = mutableStateListOf<String>(); private set

    fun fetch(restaurantId: Int) {
        viewModelScope.launch {
            uiState = fetchReviewUseCase.invoke(restaurantId)
        }
    }

    fun onLike(reviewId : Int){
        Log.d(tag, "onLike reviewId: $reviewId")
        viewModelScope.launch {
            try {
                onLikeUseCase.invoke(reviewId)
            }catch (e : Exception){
                e.message?.let {
                    errorMessage.add(it)
                    Log.e(tag, it)
                }
            }
        }
    }

    fun onFavorite(reviewId : Int){
        Log.d(tag, "onFavorite reviewId: $reviewId")
    }
}