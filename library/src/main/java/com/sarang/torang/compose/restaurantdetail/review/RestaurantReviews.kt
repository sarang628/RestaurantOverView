package com.sarang.library.compose.restaurantdetail.review

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sarang.library.data.ReviewRowData
import com.sarang.library.compose.RestaurantInfoTitle
import com.sarang.torang.compose.restaurant.detail.components.AndroidViewRatingBar

@Composable
fun RestaurantReviews(viewModel: RestaurantReviewViewModel = hiltViewModel(), restaurantId : Int, progressTintColor: Color? = null, onProfile: (Int) -> Unit, onContents: (Int) -> Unit, ) {
    val uiState = viewModel.uiState
    LaunchedEffect(restaurantId) {
        viewModel.fetch(restaurantId)
    }
    if (uiState.isNotEmpty()) {
        RestaurantReviews(uiState, progressTintColor, onProfile, onContents)
    }
}

@Composable
private fun RestaurantReviews(reviewRowData: List<ReviewRowData> = listOf(), progressTintColor: Color? = null, onProfile: (Int) -> Unit, onContents: (Int) -> Unit, ) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp)
    ) {
        RestaurantInfoTitle("Review")
        Spacer(modifier = Modifier.height(8.dp))
        for (reviewData in reviewRowData) {
            ReviewRow(
                name = reviewData.name,
                fullName = reviewData.fullName,
                rating = reviewData.rating,
                comment = reviewData.comment,
                progressTintColor = progressTintColor,
                onProfile = { onProfile.invoke(reviewData.userId) },
                onContents = { onContents.invoke(reviewData.reviewId) }
            )
        }
    }
}


@Composable
fun ReviewRow(name: String, fullName: String, rating: Float, comment: String, progressTintColor: Color? = null, onProfile: () -> Unit, onContents: () -> Unit, ) {
    Row(Modifier.fillMaxWidth()) {
        Box(modifier = Modifier.size(35.dp), contentAlignment = Alignment.Center) {
            Button(onClick = onProfile, modifier = Modifier.size(35.dp)) {}
            Text(text = if (name.length > 2) name.substring(0, 2) else name, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Row {
                Text(text = fullName, Modifier.clickable { onProfile.invoke() })
                AndroidViewRatingBar(rating = rating, progressTintColor = progressTintColor)
                Text(text = rating.toString())
            }
            Text(text = comment, maxLines = 3, overflow = TextOverflow.Ellipsis, modifier = Modifier.clickable { onContents.invoke() }) }
    }
    Spacer(modifier = Modifier.height(4.dp))
}

@Preview
@Composable
fun PreviewReviews() {
    val reviews = ArrayList<ReviewRowData>()
        .apply {
            add(
                ReviewRowData(
                    name = "JM",
                    fullName = "강아지",
                    rating = 3.0f,
                    comment = "서비스가 훌륭함",
                    reviewId = 0,
                    userId = 0
                )
            )
            add(
                ReviewRowData(
                    name = "DY",
                    fullName = "대한민국",
                    rating = 3.0f,
                    comment = "맛있음",
                    reviewId = 0,
                    userId = 0
                )
            )
            add(
                ReviewRowData(
                    name = "CA",
                    fullName = "희망찬",
                    rating = 3.0f,
                    comment = "저렴함",
                    reviewId = 0,
                    userId = 0
                )
            )
            add(
                ReviewRowData(
                    name = "OY",
                    fullName = "고양이",
                    rating = 3.0f,
                    comment = "가까움",
                    reviewId = 0,
                    userId = 0
                )
            )
        }
    RestaurantReviews(reviews, onContents = {}, onProfile = {})
}