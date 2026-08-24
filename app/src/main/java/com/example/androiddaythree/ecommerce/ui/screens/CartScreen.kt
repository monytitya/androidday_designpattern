package com.example.androiddaythree.ecommerce.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddaythree.ecommerce.data.CartItem
import com.example.androiddaythree.ecommerce.data.sampleProducts
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(onBack: () -> Unit) {
    var cartItems by remember { 
        mutableStateOf(sampleProducts.take(3).map { CartItem(it) }) 
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("My Cart", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
        bottomBar = {
            CartSummary(items = cartItems)
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(cartItems, key = { it.product.id }) { item ->
                var isVisible by remember { mutableStateOf(true) }
                
                AnimatedVisibility(
                    visible = isVisible,
                    exit = shrinkVertically() + fadeOut()
                ) {
                    CartItemRow(
                        item = item,
                        onRemove = {
                            isVisible = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CartItemRow(item: CartItem, onRemove: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.LightGray)
                .padding(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        when (item.product.id % 4) {
                            1 -> Color.Red
                            2 -> Color.Blue
                            3 -> Color.Green
                            else -> Color.Black
                        },
                        RoundedCornerShape(8.dp)
                    )
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(text = item.product.name, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text(text = item.product.type, color = Color.Gray, fontSize = 12.sp)
            Text(text = "$${item.product.price}", fontWeight = FontWeight.Black)
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            IconButton(onClick = onRemove) {
                Icon(Icons.Default.Delete, contentDescription = null, tint = Color.Red)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("-", modifier = Modifier.padding(horizontal = 4.dp))
                Text(text = item.quantity.toString(), fontWeight = FontWeight.Bold)
                Text("+", modifier = Modifier.padding(horizontal = 4.dp))
            }
        }
    }
}

@Composable
fun CartSummary(items: List<CartItem>) {
    val subtotal = items.sumOf { it.product.price * it.quantity }
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Subtotal", color = Color.Gray)
            Text("$${String.format(Locale.US, "%.2f", subtotal)}")
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Delivery Fee", color = Color.Gray)
            Text("$10.00")
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Discount", color = Color.Gray)
            Text("10%", color = Color.Red)
        }
        
        HorizontalDivider(Modifier.padding(vertical = 16.dp))
        
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Total", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text("$${String.format(Locale.US, "%.2f", subtotal + 10 - (subtotal * 0.1))}", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            shape = RoundedCornerShape(28.dp)
        ) {
            Text("Check out", color = Color.Black, fontWeight = FontWeight.Bold)
        }
    }
}
