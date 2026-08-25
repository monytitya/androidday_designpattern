package com.example.androiddaythree.ecommerce.data

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int,
    val name: String,
    val brand: String,
    val price: Double,
    val originalPrice: Double? = null,
    val description: String,
    val type: String,
    val images: List<Int>,
    val rating: Double = 4.5,
    val reviews: Int = 10000
)

data class CartItem(
    val product: Product,
    var quantity: Int = 1
)
