package com.sarang.library.data

data class MenuData(val menuName: String, val price: Float, val url: String) {
    companion object {
        fun empty(): MenuData = MenuData("", 0f, "")
    }
}

fun testMenuData(): MenuData {
    return MenuData(
        menuName = "menuName",
        price = 10000.0f,
        url = ""
    )
}