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
import com.sarang.torang.usecase.restaurantoverview.IsLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantFeedsViewModel @Inject constructor(
    val fetchReviewUseCase  : FetchReviewsUseCase,
    val clickLikeUseCase    : ClickLikeUseCase,
    val isLoginUseCase      : IsLoginUseCase
) : ViewModel() {

    var uiState: List<FeedInRestaurant> by mutableStateOf(ArrayList())
    val isLogin : StateFlow<Boolean> = isLoginUseCase.invoke().stateIn(
        scope = viewModelScope, initialValue = false, started = SharingStarted.WhileSubscribed(5000)
    )
    val tag = "__RestaurantFeedsViewModel"
    private var _errorMessage : MutableStateFlow<List<String>> = MutableStateFlow(listOf());
    var errorMessage : StateFlow<List<String>> = _errorMessage
    var fetch : Job? = null

    fun fetch(restaurantId: Int) {
        fetch?.cancel()
        fetch = viewModelScope.launch {
            fetchReviewUseCase.invoke(restaurantId).collectLatest {
                Log.d(tag, "collected : ${it.map { 
                    "${it.reviewImages[0].width} * ${it.reviewImages[0].height}" 
                }}")
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