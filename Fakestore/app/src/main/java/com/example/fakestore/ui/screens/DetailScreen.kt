package com.example.fakestore.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.fakestore.viewmodel.ProductViewModel
import com.example.fakestore.data.model.Result

@Composable
fun DetailScreen(viewModel: ProductViewModel, productId: Int, navController: NavController) {
    LaunchedEffect(productId) {
        viewModel.fetchProduct(productId)
    }

    when (val state = viewModel.productDetailState) {
        is Result.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is Result.Success -> {
            val product = state.data
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AsyncImage(
                    model = product.image,
                    contentDescription = product.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
                Text(text = product.title, style = MaterialTheme.typography.headlineSmall)
                Text(text = "$${product.price}", style = MaterialTheme.typography.titleLarge)
                Text(text = product.description, style = MaterialTheme.typography.bodyLarge)
                Button(onClick = { navController.popBackStack() }) {
                    Text("Back")
                }
            }
        }
        is Result.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Error: ${state.message}")
            }
        }
    }
}