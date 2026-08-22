package com.example.androiddaythree.animations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FullAnimationsDemoScreen() {
    var isVisible by remember { mutableStateOf(true) }
    var isRotated by remember { mutableStateOf(false) }
    var isBlue by remember { mutableStateOf(true) }

    val rotation by animateFloatAsState(
        targetValue = if (isRotated) 360f else 0f,
        animationSpec = tween(durationMillis = 1000)
    )

    val backgroundColor by animateColorAsState(
        targetValue = if (isBlue) Color.Blue else Color.Magenta,
        animationSpec = tween(durationMillis = 1000)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Animations & Transitions Demo", style = MaterialTheme.typography.headlineMedium)

        // 1. AnimatedVisibility
        Button(onClick = { isVisible = !isVisible }) {
            Text(text = if (isVisible) "Hide Box" else "Show Box")
        }
        
        AnimatedVisibility(visible = isVisible) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Green, RoundedCornerShape(8.dp))
            )
        }

        // 2. Motion (Rotation)
        Text(text = "Motion (Rotation)")
        Box(
            modifier = Modifier
                .size(100.dp)
                .rotate(rotation)
                .background(Color(0xFF0048FF)) // Orange
                .clickable { isRotated = !isRotated },
            contentAlignment = Alignment.Center
        ) {
            Text("Tap to Rotate")
        }

        // 3. Color Transition
        Text(text = "Transition (Color)")
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(50.dp))
                .background(backgroundColor)
                .clickable { isBlue = !isBlue },
            contentAlignment = Alignment.Center
        ) {
            Text("Tap to Change", color = Color.White)
        }
    }
}
