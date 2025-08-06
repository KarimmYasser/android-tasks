package com.example.fakestore.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fakestore.ui.screens.DetailScreen
import com.example.fakestore.ui.screens.HomeScreen
import com.example.fakestore.viewmodel.ProductViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FakeStoreApp()
        }
    }
}

@Composable
fun FakeStoreApp() {
    val navController = rememberNavController()
    val viewModel: ProductViewModel = remember { ProductViewModel() }
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(padding)
        ) {
            composable("home") {
                HomeScreen(viewModel, navController)
            }
            composable("detail/{productId}") { backStackEntry ->
                val productId = backStackEntry.arguments?.getString("productId")?.toIntOrNull() ?: 0
                DetailScreen(viewModel, productId, navController)
            }
        }

        // Show error snackbar
        LaunchedEffect(viewModel.errorMessage) {
            viewModel.errorMessage?.let { message ->
                snackbarHostState.showSnackbar(message)
            }
        }
    }
}