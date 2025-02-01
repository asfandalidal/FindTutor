package com.example.findtutor.presentation.main_screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.findtutor.ui.theme.PrimaryColor
import com.example.findtutor.ui.theme.poppinsBold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivacyPolicyScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Privacy Policy",
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
                    // Introduction Section
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
                                text = "Introduction",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryColor
                                ),
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "This Privacy Policy describes how we collect, use, and protect your personal information when you use TutorFinder. We are committed to ensuring your privacy is protected.",
                                style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black)
                            )
                        }
                    }
                }

                item {
                    // Information Collection Section
                    Spacer(modifier = Modifier.height(10.dp)) //16
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
                                text = "Information Collection",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryColor
                                ),
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "We collect personal information such as your name, email address, phone number, and location when you use our app. This information is used to provide and improve our services.",
                                style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black)
                            )
                        }
                    }
                }

                item {
                    // Use of Information Section
                    Spacer(modifier = Modifier.height(10.dp))
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
                                text = "Use of Information",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryColor
                                ),
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "We use your information to connect you with tutors, process your requests, and communicate with you. We do not sell or share your information with third parties without your consent.",
                                style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black)
                            )
                        }
                    }
                }

                item {
                    // Security Section
                    Spacer(modifier = Modifier.height(10.dp))
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
                                text = "Security",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryColor
                                ),
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "We are committed to ensuring your information is secure. We implement appropriate measures to protect your data from unauthorized access, alteration, or disclosure.",
                                style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black)
                            )
                        }
                    }
                }

            }
        }
    )
}
