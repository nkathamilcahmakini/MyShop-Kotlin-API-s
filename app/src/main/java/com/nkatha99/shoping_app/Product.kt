package com.nkatha99.shoping_app

import android.accounts.AuthenticatorDescription

data class Product(
    var id: Int,
    var title: String,
    var description: String,
    var price: Double,
    var rating: Double,
    var stock: Int,
    var category: String,
    var thumbnail: String
)
