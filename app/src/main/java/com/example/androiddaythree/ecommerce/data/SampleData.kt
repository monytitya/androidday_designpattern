package com.example.androiddaythree.ecommerce.data

import android.R.attr.id
import com.example.androiddaythree.R

val sampleProducts = listOf(
    Product(
        id = 1,
        name = "Picun ANC Wireless Headphones",
        brand = "Picun",
        price = 89.99,
        originalPrice = 129.99,
        description = "High-fidelity sound with Active Noise Cancellation. Comfortable over-ear design for long listening sessions.",
        type = "Electronics",
        images = listOf(R.drawable.img_1)
    ),
    Product(
        id = 2,
        name = "Realme Buds Air 5 Pro",
        brand = "Realme",
        price = 59.99,
        description = "Pure bass, wireless freedom. 40 hours of battery life and smart touch controls.",
        type = "Electronics",
        images = listOf(R.drawable.img_2)
    ),
    Product(
        id = 3,
        name = "Premium Wired Earbuds",
        brand = "Generic",
        price = 19.99,
        description = "Classic wired connection with crystal clear audio and tangle-free cable.",
        type = "Electronics",
        images = listOf(R.drawable.img_3)
    ),
    Product(
        id = 4,
        name = "Modern Business Laptop",
        brand = "TechPro",
        price = 899.00,
        originalPrice = 999.00,
        description = "Powerful performance meets sleek design. Perfect for work, study, and creative projects.",
        type = "Electronics",
        images = listOf(R.drawable.img_4)
    ),
    Product(
        id = 5,
        name = "Gaming Keyboard & Mouse Combo",
        brand = "GameX",
        price = 45.50,
        description = "RGB mechanical feel keyboard with high-precision gaming mouse. Elevate your gaming experience.",
        type = "Gaming",
        images = listOf(R.drawable.img_5)
    ),
    Product(
        id = 6,
        name = "Nike Blazer Low '77 Jumbo",
        brand = "Nike",
        price = 120.11,
        description = "Styled for the '70s. Loved in the '80s. Classic in the '90s. Ready for the future.",
        type = "Women's Shoes",
        images = listOf(R.drawable.img_6)
    )
)

val sampleCategories = listOf("All", "Electronics", "Gaming", "Nike", "Adidas")
