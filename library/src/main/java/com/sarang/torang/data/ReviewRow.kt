package com.sarang.library.data

data class ReviewRowData(
    val name: String,
    val fullName: String,
    val rating: Float,
    val comment: String,
    val userId : Int,
    val reviewId : Int
){
    companion object{
        fun empty() : ReviewRowData{
            return ReviewRowData(name = "", fullName = "", rating = 0f, comment = "", reviewId = 0, userId = 0)
        }
    }
}

fun testReviewRowData(): ReviewRowData {
    return ReviewRowData(name = "name", fullName = "fullName", rating = 3.5f, comment = "comment", reviewId = 0, userId = 0)
}