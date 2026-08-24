package com.example.androiddaythree.ecommerce.data

val sampleProducts = listOf(
    Product(
        id = 1,
        name = "Nike Blazer Low '77 Jumbo",
        brand = "Nike",
        price = 120.11,
        description = "Styled for the '70s. Loved in the '80s. Classic in the '90s. Ready for the future. The Nike Blazer Mid '77 Next...",
        type = "Women's Shoes",
        images = listOf() // Using colors for demo
    ),
    Product(
        id = 2,
        name = "Nike Air Force 1 '07 LV8 Next Nature",
        brand = "Nike",
        price = 320.11,
        description = "Legendary style, refined. The original hoop shoe that puts a fresh spin on what you know best.",
        type = "Men's Shoes",
        images = listOf()
    ),
    Product(
        id = 3,
        name = "Nike Blazer Mid '77",
        brand = "Nike",
        price = 100.99,
        originalPrice = 110.99,
        description = "The Nike Blazer Mid '77 harnesses the old-school look of Nike basketball with a retro midsole finish.",
        type = "Woman's Shoes",
        images = listOf()
    ),
    Product(
        id = 4,
        name = "Air Jordan 7 Retro SE",
        brand = "Jordan",
        price = 120.11,
        description = "Inspired by the shoe MJ wore during the '92 season and the summer of '92.",
        type = "Man's Shoes",
        images = listOf()
    )
)

val sampleCategories = listOf("All", "Nike", "Adidas", "New Balance", "Puma")
