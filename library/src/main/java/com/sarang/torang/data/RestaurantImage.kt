package com.sarang.library.data

data class RestaurantImage(
    val id: Int,
    val url: String
) {
    companion object {
        fun empty(): RestaurantImage = RestaurantImage(0, "")
    }
}

fun testRestaurantImage(): RestaurantImage {
    return RestaurantImage(id = 2, url = "http://sarang628.iptime.org:89/restaurants/2.jpeg")
}