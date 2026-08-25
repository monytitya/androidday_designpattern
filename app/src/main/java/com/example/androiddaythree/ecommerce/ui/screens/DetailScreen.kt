package com.example.androiddaythree.ecommerce.ui.screens

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddaythree.ecommerce.data.sampleProducts
import com.example.androiddaythree.ecommerce.ui.theme.LightGrey

import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@OptIn(androidx.compose.animation.ExperimentalSharedTransitionApi::class)
@Composable
fun DetailScreen(
    productId: Int,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    onBack: () -> Unit
) {
    val product = sampleProducts.find { it.id == productId } ?: return

    Scaffold(
        bottomBar = {
            BottomBar(price = product.price)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                }
                Text(text = "Prodcuts details", fontWeight = FontWeight.Black, fontSize = 18.sp)
                IconButton(onClick = { }) {
                    Icon(Icons.Default.MoreVert, contentDescription = null)
                }
            }

            // Shared Image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(32.dp))
                    .background(LightGrey),
                contentAlignment = Alignment.Center
            ) {
                with(sharedTransitionScope) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .sharedElement(
                                rememberSharedContentState(key = "product_image_${product.id}"),
                                animatedVisibilityScope = animatedContentScope
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        if (product.images.isNotEmpty()) {
                            Image(
                                painter = painterResource(id = product.images.first()),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Fit
                            )
                        } else {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize(0.6f)
                                    .background(
                                        color = when (product.id % 4) {
                                            1 -> Color.Red
                                            2 -> Color.Blue
                                            3 -> Color.Green
                                            else -> Color.Black
                                        }
                                    )
                            )
                        }
                    }
                }
            }

            Column(modifier = Modifier.padding(24.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = product.name,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        Icons.Default.Favorite,
                        contentDescription = null,
                        tint = Color.Red,
                        modifier = Modifier.size(24.dp)
                    )
                }
                
                Text(text = product.type, color = Color.Gray, fontSize = 14.sp)
                
                Spacer(modifier = Modifier.height(16.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "$${product.price}",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Black
                    )
                    product.originalPrice?.let {
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "$$it",
                            color = Color.Gray,
                            fontSize = 16.sp,
                            textDecoration = androidx.compose.ui.text.style.TextDecoration.LineThrough
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Sizes
                Text(text = "Size", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(12.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf(32, 33, 34, 35, 36, 37).forEach { size ->
                        SizeChip(size = size, isSelected = size == 32)
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(text = "Description", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = product.description,
                    color = Color.Gray,
                    lineHeight = 20.sp
                )
            }
        }
    }
}

@Composable
fun SizeChip(size: Int, isSelected: Boolean) {
    Surface(
        color = if (isSelected) Color.Black else Color.Transparent,
        shape = CircleShape,
        border = if (isSelected) null else BorderStroke(1.dp, Color.LightGray),
        modifier = Modifier.size(45.dp)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = size.toString(),
                color = if (isSelected) Color.White else Color.Black,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun BottomBar(price: Double) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            IconButton(onClick = { }) { Text("-", fontSize = 24.sp) }
            Text(text = "1", modifier = Modifier.padding(horizontal = 8.dp), fontWeight = FontWeight.Bold)
            IconButton(onClick = { }) { Text("+", fontSize = 24.sp) }
        }
        
        Button(
            onClick = { },
            modifier = Modifier.height(56.dp).weight(2f),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            shape = RoundedCornerShape(28.dp)
        ) {
            Text(text = "Add to cart", color = Color.Black, fontWeight = FontWeight.Bold)
        }
    }
}
