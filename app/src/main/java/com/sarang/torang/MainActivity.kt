package com.sarang.torang

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sarang.torang.di.restaurant_overview_di.ProvideRestaurantOverview
import com.sarang.torang.repository.FindRepository
import com.sarang.torang.repository.LoginRepository
import com.sarang.torang.repository.test.LoginRepositoryTest
import com.sryang.torang.ui.TorangTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var findRepository : FindRepository
    @Inject lateinit var loginRepository: LoginRepository

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TorangTheme {

                Box(Modifier.fillMaxSize()){
                    Test(loginRepository)
                }

            }
        }
    }

    @Composable
    fun Test(loginRepository: LoginRepository){
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
            composable("LoginRepository"){
                LoginRepositoryTest(loginRepository)
            }
            composable("RestaurantOverView"){
                OverViewTest()
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun OverViewTest(){
        val restaurants by findRepository.restaurants.collectAsStateWithLifecycle()
        val scaffoldState = rememberBottomSheetScaffoldState()
        val scope = rememberCoroutineScope()
        var restaurantId by remember { mutableStateOf(0) }
        val context = LocalContext.current
        val snackbarHostState = remember { SnackbarHostState() }


        val sheetContent: @Composable () -> Unit = {
            LazyColumn(Modifier.fillMaxSize()) {
                items(restaurants.reversed()) {
                    TextButton({
                        restaurantId = it.restaurant.restaurantId
                        scope.launch {
                            scaffoldState.bottomSheetState.partialExpand()
                        }
                    }) {
                        Text("${it.restaurant.restaurantName}(${it.restaurant.restaurantId})")
                    }
                }
            }
        }

        val content : @Composable () -> Unit = {
            Box(Modifier.fillMaxSize()){
                ProvideRestaurantOverview(
                    restaurantId = restaurantId,
                    onErrorMessage = {
                        scope.launch {
                            snackbarHostState.showSnackbar(it)
                        }
                    }
                )

                FloatingActionButton(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(bottom = 24.dp, end = 12.dp),
                    onClick = {
                        scope.launch {
                            scope.launch {
                                findRepository.findFilter()
                            }
                            scaffoldState.bottomSheetState.expand()
                        }
                    }) {
                    Icon(Icons.AutoMirrored.Default.List, null)
                }
            }
        }

        BottomSheetScaffold(
            scaffoldState = scaffoldState,
            sheetPeekHeight = 0.dp,
            sheetContent = { sheetContent.invoke() },
            snackbarHost = {
                SnackbarHost(
                    hostState = snackbarHostState
                )
            }
        ) {
            content()
        }
    }
}

