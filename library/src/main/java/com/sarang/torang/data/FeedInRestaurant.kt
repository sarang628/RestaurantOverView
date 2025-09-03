package com.sarang.torang.data

data class FeedInRestaurant(
    val reviewId: Int,
    val restaurantId: Int,
    val userId: Int,
    val name: String,
    val restaurantName: String,
    val rating: Float,
    val profilePictureUrl: String,
    val likeAmount: Int,
    val commentAmount: Int,
    val author: String,
    val author1: String,
    val author2: String,
    val comment: String,
    val comment1: String,
    val comment2: String,
    val isLike: Boolean,
    val isFavorite: Boolean,
    val visibleLike: Boolean,
    val visibleComment: Boolean,
    val contents: String,
    val reviewImages: List<FeedImageInRestaurant> = listOf()
) {
    companion object {
        fun empty() = FeedInRestaurant(
            reviewId = 0,
            restaurantId = 0,
            userId = 0,
            name = "",
            restaurantName = "",
            rating = 0.0f,
            profilePictureUrl = "",
            likeAmount = 0,
            commentAmount = 0,
            author = "author",
            author1 = "author1",
            author2 = "author2",
            comment = "comment",
            comment1 = "comment1",
            comment2 = "comment2",
            isLike = true,
            isFavorite = true,
            visibleLike = true,
            visibleComment = true,
            contents = "contents",
            reviewImages = listOf()
        )
    }
}