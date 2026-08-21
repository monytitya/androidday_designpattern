package com.example.androiddaythree

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            setContent {
                AnimationDemoTheme {
                    FullAnimationsDemoScreen()
                }
            }
        }
    }

    annotation class AnimationDemoTheme(val value: Any)
}
