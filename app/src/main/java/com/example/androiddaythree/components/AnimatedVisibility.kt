package com.example.androiddaythree.components

import androidx.compose.runtime.remember

var isVisible by remember { mutableStateOf(false) }

Button(onClick = { isVisible = !isVisible }) {
    Text("ចុចទីនេះ")
}

// ពេល isVisible = true វា sliding ចូល និង fade ចូល
AnimatedVisibility(
visible = isVisible,
enter = fadeIn() + slideInVertically(), // ចលនាពេលចូល
exit = fadeOut() + slideOutVertically()  // ចលនាពេលចេញ
) {
    Text(
        text = "ជំរាបសួរ! នេះជាអត្ថបទគំរូ",
        modifier = Modifier.background(Color.Yellow).padding(16.dp)
    )
}