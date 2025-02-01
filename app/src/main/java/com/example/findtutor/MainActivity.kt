package com.example.findtutor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.findtutor.presentation.navigation.NavGraph
import com.example.findtutor.ui.theme.FindTutorTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FindTutorTheme {
                val navController = rememberNavController()
//                TeacherListScreen()
                NavGraph(navController)
                }
            }
        }
    }


