package com.example.androiddaythree.ecommerce.ui.screens

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddaythree.R
import com.example.androiddaythree.ecommerce.data.sampleCategories
import com.example.androiddaythree.ecommerce.data.sampleProducts
import com.example.androiddaythree.ecommerce.ui.components.CategoryChip
import com.example.androiddaythree.ecommerce.ui.components.ProductCard
import com.example.androiddaythree.ecommerce.ui.theme.ShoeBlack

@OptIn(androidx.compose.animation.ExperimentalSharedTransitionApi::class)
@Composable
fun HomeScreen(
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    onProductClick: (Int) -> Unit
) {
    var selectedCategory by remember { mutableStateOf("All") }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Top Bar
        item(key = "top_bar", contentType = "header") {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { }) {
                    Icon(Icons.Default.Menu, contentDescription = null)
                }
                Text(text = "Shopink", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                IconButton(onClick = { }) {
                    Icon(Icons.Default.Notifications, contentDescription = null)
                }
            }
        }

        // Sale Banner
        item(key = "sale_banner", contentType = "banner") {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(ShoeBlack)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.banner),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    alpha = 0.6f
                )

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Get your\nspecial sale\nup to 50%",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(text = "Shop now", color = Color.Black)
                    }
                }
            }
        }

        // New Arrival Header
        item(key = "new_arrival_header", contentType = "header") {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "New Arrival", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(text = "See all", color = Color.Gray, fontSize = 14.sp)
            }
        }

        // Categories
        item(key = "categories_list", contentType = "list") {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(
                    items = sampleCategories,
                    key = { it },
                    contentType = { "category" }
                ) { category ->
                    CategoryChip(
                        category = category,
                        isSelected = selectedCategory == category,
                        onClick = { selectedCategory = category }
                    )
                }
            }
        }

        // Product Grid (Horizontal)
        item(key = "product_list", contentType = "list") {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 8.dp)
            ) {
                items(
                    items = sampleProducts,
                    key = { it.id },
                    contentType = { "product_card" }
                ) { product ->
                    ProductCard(
                        product = product,
                        sharedTransitionScope = sharedTransitionScope,
                        animatedContentScope = animatedContentScope,
                        onClick = { onProductClick(product.id) }
                    )
                }
            }
        }

        // Best Seller Header
        item(key = "best_seller_header", contentType = "header") {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Best Seller", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(text = "See all", color = Color.Gray, fontSize = 14.sp)
            }
        }
        
        // Best Seller Items
        items(
            items = sampleProducts.takeLast(2),
            key = { "best_seller_${it.id}" },
            contentType = { "best_seller_item" }
        ) { product ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.LightGray)
                ) {
                    if (product.images.isNotEmpty()) {
                        Image(
                            painter = painterResource(id = product.images.first()),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = product.name, fontWeight = FontWeight.Bold)
                    Text(text = "$${product.price}", color = Color.Gray)
                }
            }
        }
    }
}
