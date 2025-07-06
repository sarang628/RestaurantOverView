package com.sarang.library.compose.restaurantdetail.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sarang.library.data.MenuData
import com.sarang.library.compose.RestaurantInfoTitle


@Composable
fun RestaurantMenus(
    viewModel: RestaurantMenuViewModel = hiltViewModel(),
    restaurantId: Int
) {
    val uiState = viewModel.uiState

    LaunchedEffect(restaurantId) {
        viewModel.loadMenu(restaurantId)
    }

    Column(Modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp)) {
        if (uiState.isNotEmpty()) {
            RestaurantInfoTitle("Menu")
            Spacer(modifier = Modifier.height(8.dp))
            for (i in 0 until uiState.size) {
                RestaurantMenu1(menuData1 = uiState[i])
                HorizontalDivider(Modifier.height(1.dp))
            }
        }
    }
}

@Composable
private fun RestaurantMenus(menus: List<MenuData>?) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp)
    ) {
        if (!menus.isNullOrEmpty()) {
            RestaurantInfoTitle("Menu")
            Spacer(modifier = Modifier.height(8.dp))
            for (i in 0 until menus.size) {
                RestaurantMenu1(menuData1 = menus[i])
                HorizontalDivider(Modifier.height(1.dp))
            }
        }
    }
}

@Composable
fun RestaurantMenu1(menuData1: MenuData) {
    Row {
        Row(Modifier.weight(1f)) {
            Text(text = menuData1.menuName + "..." + menuData1.price.toString())
        }
    }
}

@Preview
@Composable
fun PreviewMenus() {
    RestaurantMenus(menus = ArrayList<MenuData>().apply {
        add(
            MenuData(
                menuName = "스테이크스테이크스테이크스테이크스테이크스테이크스테이크스테이크스테이크스테이크스테이크스테이크스테이크스테이크스테이크스테이크",
                price = 30000f,
                url = ""
            )
        )
        add(MenuData(menuName = "파스타", price = 300000f, url = ""))
        add(MenuData(menuName = "커피", price = 300000f, url = ""))
        add(MenuData(menuName = "디저트", price = 300000f, url = ""))
        add(MenuData(menuName = "와인", price = 300000f, url = ""))
        add(MenuData(menuName = "에피타이저", price = 300000f, url = ""))
        add(MenuData(menuName = "샐러드", price = 300000f, url = ""))
    })

}