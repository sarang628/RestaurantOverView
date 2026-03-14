package com.sarang.torang

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun RestaurantOverViewTestMenu(loginRepositoryTest : @Composable () -> Unit = {},
                               overViewTest : @Composable () -> Unit = {}){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Menu"){
        composable("Menu"){
            Column {
                TextButton({
                    navController.navigate("loginRepository")
                }) {
                    Text("LoginRepository")
                }

                TextButton({
                    navController.navigate("RestaurantOverView")
                }) {
                    Text("RestaurantOverView")
                }
            }
        }
        composable("LoginRepository"){ loginRepositoryTest() }
        composable("RestaurantOverView"){ overViewTest() }
    }
}
