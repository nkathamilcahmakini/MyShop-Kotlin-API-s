package com.nkatha99.shoping_app

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("/product")
    fun getProducts(): Call<ProductsResponse>

    @GET("/product/{id}")
    fun getProductById(@Path("id") productId: Int): Call<Product>
}