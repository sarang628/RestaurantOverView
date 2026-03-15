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
                               restaurantOverView : @Composable () -> Unit = {},
                               restaurantImages : @Composable () -> Unit = {},
                               restaurantMenus : @Composable () -> Unit = {},
                               restaurantReviewSummary : @Composable () -> Unit = {},
                               restaurantFeeds : @Composable () -> Unit = {},
                               restaurantReservation : @Composable () -> Unit = {},
                               restaurantInfoTitle : @Composable () -> Unit = {},
                               ){
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
                    navController.navigate("restaurantOverView")
                }) {
                    Text("RestaurantOverView")
                }

                TextButton({
                    navController.navigate("RestaurantImages")
                }) {
                    Text("RestaurantImages")
                }

                TextButton({
                    navController.navigate("RestaurantMenus")
                }) {
                    Text("RestaurantMenus")
                }

                TextButton({
                    navController.navigate("RestaurantReviewSummary")
                }) {
                    Text("RestaurantReviewSummary")
                }

                TextButton({
                    navController.navigate("RestaurantFeeds")
                }) {
                    Text("RestaurantFeeds")
                }

                TextButton({
                    navController.navigate("RestaurantReservation")
                }) {
                    Text("RestaurantReservation")
                }

                TextButton({
                    navController.navigate("RestaurantInfoTitle")
                }) {
                    Text("RestaurantInfoTitle")
                }

            }
        }
        composable("LoginRepository"){ loginRepositoryTest() }
        composable("RestaurantOverView"){ restaurantOverView() }
        composable("RestaurantImages"){ restaurantImages() }
        composable("RestaurantMenus"){ restaurantMenus() }
        composable("RestaurantReviewSummary"){ restaurantReviewSummary() }
        composable("RestaurantFeeds"){ restaurantFeeds() }
        composable("RestaurantReservation"){ restaurantReservation() }
        composable("RestaurantInfoTitle"){ restaurantInfoTitle() }
    }
}
