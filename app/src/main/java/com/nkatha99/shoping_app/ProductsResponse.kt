package com.nkatha99.shoping_app

data class ProductsResponse(
    var products: List<Product>,
    var total: Int,
    var skip: Int,
    var limit: Int
)
