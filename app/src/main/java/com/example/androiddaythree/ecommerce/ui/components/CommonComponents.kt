package com.example.androiddaythree.ecommerce.ui.components

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddaythree.ecommerce.data.Product
import com.example.androiddaythree.ecommerce.ui.theme.LightGrey

@OptIn(androidx.compose.animation.ExperimentalSharedTransitionApi::class)
@Composable
fun ProductCard(
    product: Product,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    onClick: () -> Unit
) {
    var isFavorite by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .width(180.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(LightGrey)
                    .padding(16.dp)
            ) {
                with(sharedTransitionScope) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .sharedElement(
                                rememberSharedContentState(key = "product_image_${product.id}"),
                                animatedVisibilityScope = animatedContentScope
                            )
                            .background(
                                // Different color for each product for demo
                                color = when (product.id % 4) {
                                    1 -> Color.Red
                                    2 -> Color.Blue
                                    3 -> Color.Green
                                    else -> Color.Black
                                },
                                shape = RoundedCornerShape(16.dp)
                            )
                    )
                }

                IconButton(
                    onClick = { isFavorite = !isFavorite },
                    modifier = Modifier.align(Alignment.BottomEnd)
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = null,
                        tint = if (isFavorite) Color.Red else Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = product.name,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                maxLines = 2
            )
            Text(
                text = product.type,
                color = Color.Gray,
                fontSize = 12.sp
            )
            Text(
                text = "$${product.price}",
                fontWeight = FontWeight.Black,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun CategoryChip(
    category: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier.clickable { onClick() },
        color = if (isSelected) MaterialTheme.colorScheme.primary else LightGrey,
        shape = RoundedCornerShape(24.dp)
    ) {
        Text(
            text = category,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
            fontWeight = FontWeight.Medium,
            color = if (isSelected) Color.Black else Color.Gray
        )
    }
}
