package com.sarang.library.data

data class ReviewSummaryData(
    val rating: Float = 0f,
    val totalReviewer: Int = 0,
    val score5: Float = 0f,
    val score4: Float = 0f,
    val score3: Float = 0f,
    val score2: Float = 0f,
    val score1: Float = 0f,
)

fun testReviewSummaryData(): ReviewSummaryData {
    return ReviewSummaryData(
        rating = 3.5f,
        totalReviewer = 10,
        score5 = 5.0f,
        score4 = 4.0f,
        score3 = 3.0f,
        score2 = 2.0f,
        score1 = 1.0f
    )
}