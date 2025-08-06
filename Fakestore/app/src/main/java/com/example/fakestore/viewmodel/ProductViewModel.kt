package com.example.fakestore.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestore.data.model.Product
import com.example.fakestore.data.model.Result
import com.example.fakestore.data.network.RetrofitService
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    var productsState by mutableStateOf<Result<List<Product>>>(Result.Loading)
        private set
    var productDetailState by mutableStateOf<Result<Product>>(Result.Loading)
        private set
    var errorMessage by mutableStateOf<String?>(null)
        private set

    init {
        fetchProducts()
    }

    fun fetchProducts() {
        viewModelScope.launch {
            productsState = Result.Loading
            try {
                val products = RetrofitService.api.getProducts()
                productsState = Result.Success(products)
            } catch (e: Exception) {
                productsState = Result.Error(e.message ?: "Failed to load products")
                errorMessage = e.message
            }
        }
    }

    fun fetchProduct(id: Int) {
        viewModelScope.launch {
            productDetailState = Result.Loading
            try {
                val product = RetrofitService.api.getProduct(id)
                productDetailState = Result.Success(product)
            } catch (e: Exception) {
                productDetailState = Result.Error(e.message ?: "Failed to load product")
                errorMessage = e.message
            }
        }
    }
}
