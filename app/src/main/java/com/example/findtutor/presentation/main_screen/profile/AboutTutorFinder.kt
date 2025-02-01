package com.example.findtutor.presentation.main_screen.profile

import androidx.compose.foundation.background
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.findtutor.ui.theme.PrimaryColor
import com.example.findtutor.ui.theme.poppinsBold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutTutorFinderScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "About TutorFinder",
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontFamily = poppinsBold
                        )
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PrimaryColor),
                modifier = Modifier.fillMaxWidth()
            )
        },
        content = { contentPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
                    .padding(contentPadding)
                    .padding(bottom = 60.dp)
            ) {
                item {
                    // App Description Section
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        elevation = CardDefaults.cardElevation(4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "Welcome to TutorFinder",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryColor
                                ),
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "TutorFinder is an app designed to connect parents and students with qualified tutors. The app provides a seamless way to find home tutors in your area, send booking requests, and much more.",
                                style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black)
                            )
                        }
                    }
                }

                item {
                    // Mission Statement Section
                    Spacer(modifier = Modifier.height(16.dp))
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        elevation = CardDefaults.cardElevation(4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "Our Mission",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryColor
                                ),
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "At TutorFinder, we aim to make education accessible and affordable for everyone by providing a platform that brings together qualified tutors and learners. Our mission is to make learning more personalized and effective through home-based tutoring.",
                                style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black)
                            )
                        }
                    }
                }

                item {
                    // Features Section
                    Spacer(modifier = Modifier.height(16.dp))
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        elevation = CardDefaults.cardElevation(4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "Key Features",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryColor
                                ),
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            val aboutText = "• Easily find tutors based on your area location\n" +
                                    "• Browse tutor profiles with detailed information\n" +
                                    "• Directly contact tutors for personalized arrangements\n" +
                                    "• Conveniently connect with tutors for home tutoring services"
                            Text(
                                text = aboutText,
                                style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black)
                            )
                        }
                    }
                }

                item {
                    // Contact Information Section
                    Spacer(modifier = Modifier.height(16.dp))
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        elevation = CardDefaults.cardElevation(4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "Get in Touch",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryColor
                                ),
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "For any inquiries or support, feel free to contact us at:\n" +
                                        "Email: asfand.azeemi@gmail.com\n" +
                                        "Phone: 0302-3246164",
                                style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black)
                            )
                        }
                    }
                }
            }
        }
    )
}
