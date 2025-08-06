package com.example.fakestore.data.network

import com.example.fakestore.data.model.Product
import retrofit2.http.GET
import retrofit2.http.Path

interface FakeStoreApi {
    @GET("products")
    suspend fun getProducts(): List<Product>

    @GET("products/{id}")
    suspend fun getProduct(@Path("id") id: Int): Product
}