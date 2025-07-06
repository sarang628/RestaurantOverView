package com.sarang.library.compose

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun RestaurantInfoTitle(title: String) {
    Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
}

@Preview
@Composable
fun RestaurantInfoTitle() {
    RestaurantInfoTitle(title = "메뉴 타이틀")
}