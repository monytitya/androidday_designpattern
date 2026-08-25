package com.example.androiddaythree.ecommerce.ui.screens

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddaythree.ecommerce.data.sampleCategories
import com.example.androiddaythree.ecommerce.data.sampleProducts
import com.example.androiddaythree.ecommerce.ui.components.CategoryChip
import com.example.androiddaythree.ecommerce.ui.components.ProductCard
import com.example.androiddaythree.ecommerce.ui.theme.ShoeBlack

import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.androiddaythree.R

@OptIn(androidx.compose.animation.ExperimentalSharedTransitionApi::class)
@Composable
fun HomeScreen(
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    onProductClick: (Int) -> Unit
) {
    var selectedCategory by remember { mutableStateOf("All") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Top Bar
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

        Spacer(modifier = Modifier.height(16.dp))

        // Sale Banner
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(ShoeBlack)
        ) {
            // Background Image (Uncomment after adding R.drawable.sale_banner)
            /*
            Image(
                painter = painterResource(id = R.drawable.sale_banner),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                alpha = 0.6f
            )
            */

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

        Spacer(modifier = Modifier.height(24.dp))

        // New Arrival Header
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "New Arrival", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(text = "See all", color = Color.Gray, fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Categories
        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(sampleCategories) { category ->
                CategoryChip(
                    category = category,
                    isSelected = selectedCategory == category,
                    onClick = { selectedCategory = category }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Product Grid (Horizontal for demo)
        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(sampleProducts) { product ->
                ProductCard(
                    product = product,
                    sharedTransitionScope = sharedTransitionScope,
                    animatedContentScope = animatedContentScope,
                    onClick = { onProductClick(product.id) }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Best Seller Header
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Best Seller", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(text = "See all", color = Color.Gray, fontSize = 14.sp)
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Secondary List
        sampleProducts.takeLast(2).forEach { product ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                // Simplified product row
                Box(modifier = Modifier.size(80.dp).clip(RoundedCornerShape(16.dp)).background(Color.LightGray))
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = product.name, fontWeight = FontWeight.Bold)
                    Text(text = "$${product.price}", color = Color.Gray)
                }
            }
        }
    }
}
