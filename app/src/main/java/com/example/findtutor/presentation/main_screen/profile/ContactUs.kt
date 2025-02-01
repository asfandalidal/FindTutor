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
fun ContactUsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Contact Us",
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
                    // Team Information Section with Emojis
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
                                text = "Meet Our Team",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryColor
                                ),
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "We are a passionate team working towards creating a seamless learning experience. Here's our team:\n\n" +
                                        "• Asfand Ali 👨‍💻 - Android Developer\n" +
                                        "   Email: asfand.azeemi@gmail.com\n\n" +
                                        "• Jai Kumar 💻 - Backend Developer\n" +
                                        "   Email: iamjaisuthar@gmail.com\n\n" +
                                        "• Zarawar Khan & Abdul Haseeb 🎨 - UI/UX Designer\n" +
                                        "   Email: zarawarkhan@gmail.com\n\n" +
                                        "Together, we discuss and propose combined solutions to make the app better. The alpha version is live and we continue to improve it.",
                                style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black)
                            )
                        }
                    }
                }

                item {
                    // GitHub Contribution Section
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
                                text = "Contribute to the Project",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryColor
                                ),
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "You can contribute to the TutorFinder project on GitHub. Join us in improving this app and making learning easier for everyone!\n\n" +
                                        "GitHub: https://github.com/asfandalidal/TutorFinderApp",
                                style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black)
                            )
                        }
                    }
                }
            }
        }
    )
}
