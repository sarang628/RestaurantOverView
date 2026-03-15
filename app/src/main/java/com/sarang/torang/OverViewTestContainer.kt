package com.sarang.torang

import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sarang.torang.repository.FindRepository
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OverViewTestContainer(findRepository : FindRepository,
                          overviewCompose : @Composable (Int, SnackbarHostState) -> Unit = {_,_->}){
    val restaurants by findRepository.restaurants.collectAsStateWithLifecycle(emptyList())
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()
    var restaurantId by remember { mutableStateOf(301) }
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
            overviewCompose(restaurantId, snackbarHostState)
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

