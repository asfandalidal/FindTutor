package com.example.findtutor.presentation.main_screen.profile

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ContactSupport
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.findtutor.presentation.auth.CustomButton
import com.example.findtutor.ui.theme.PrimaryColor
import com.example.findtutor.ui.theme.poppinsBold
import com.example.findtutor.presentation.main_screen.MainViewModel
import com.example.findtutor.ui.theme.poppinsRegular
import java.io.File


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController, viewModel: MainViewModel = hiltViewModel()) {

    val username by viewModel.username
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Profile",
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
                    .background(Color.White)
                    .padding(contentPadding)
                    .padding(bottom = 16.dp)
            ) {
                item {
                    // Profile Section
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 2.dp),
                        elevation = CardDefaults.cardElevation(1.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.AccountCircle,
                                contentDescription = "Profile Icon",
                                modifier = Modifier
                                    .size(80.dp)
                                    .padding(end = 8.dp),
                                tint = PrimaryColor
                            )
                            Row(
                                modifier = Modifier.weight(1f),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    username?.let {
                                        Text(
                                            text = it,
                                            style = MaterialTheme.typography.titleMedium.copy(
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 16.sp,
                                                fontFamily = poppinsBold
                                            ),
                                            color = Color.Black,
                                            maxLines = 1,
                                            softWrap = false
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = "View & edit your profile",
                                        style = MaterialTheme.typography.bodySmall.copy(
                                            color = Color.Gray,
                                            fontFamily = poppinsRegular
                                        ),
                                        maxLines = 1,
                                        softWrap = false
                                    )
                                }
                                Spacer(modifier = Modifier.width(2.dp))
                                Icon(
                                    imageVector = Icons.Default.Settings,
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp),
                                    tint = PrimaryColor
                                )
                            }
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    SectionTitle("App Account")
                    CardSection {
                        ProfileItem(
                            icon = Icons.Default.AccountCircle,
                            title = "Account Settings",
                            onClick = {}
                        )
                        ProfileItem(
                            icon = Icons.Default.Favorite,
                            title = "Favorites",
                            onClick = {}
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    SectionTitle("App General")
                    CardSection {
                        ProfileItem(
                            icon = Icons.Default.ContactSupport,
                            title = "Contact Us",
                            onClick = { navController.navigate("contact_us") }
                        )
                        ProfileItem(
                            icon = Icons.Default.Share,
                            title = "Share this App",
                            onClick = {
                                val apkFile = File(
                                    context.getExternalFilesDir(null),
                                    "app-release.apk"
                                )
                                if (apkFile.exists()) {
                                    val apkUri: Uri = FileProvider.getUriForFile(
                                        context,
                                        "${context.packageName}.fileprovider",
                                        apkFile
                                    )
                                    val shareIntent = Intent().apply {
                                        action = Intent.ACTION_SEND
                                        putExtra(Intent.EXTRA_STREAM, apkUri)
                                        type = "application/vnd.android.package-archive"
                                        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                                    }
                                    context.startActivity(
                                        Intent.createChooser(
                                            shareIntent,
                                            "Share TutorFinder App"
                                        )
                                    )
                                } else {
                                    Log.d("error", "app file failure")
                                }
                            }
                        )
                        ProfileItem(
                            icon = Icons.Default.Policy,
                            title = "Terms and Privacy Policy",
                            onClick = { navController.navigate("terms") }
                        )
                        ProfileItem(
                            icon = Icons.Default.Info,
                            title = "About TutorFinder",
                            onClick = { navController.navigate("about_tutor_finder_screen") }
                        )
                    }
                }

                // Add the "Register as Tutor" button
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    CustomButton(
                        text = "Register as Tutor",
                        onClick = {
                            // Navigate to the "Register as Tutor" screen or perform other actions
                            navController.navigate("register_tutor")
                        }
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    CustomButton("Logout", onClick = {
                        viewModel.logout()
                        navController.navigate("login") {
                            popUpTo(
                                navController.previousBackStackEntry?.destination?.id
                                    ?: navController.graph.startDestinationId
                            ) {
                                inclusive = true
                            }
                        }
                    })
                }
            }
        }
    )
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleSmall.copy(
            fontWeight = FontWeight.Bold,
            color = Color.Black
        ),
        textAlign = TextAlign.Start,
        modifier = Modifier.padding(horizontal = 8.dp)
    )
}

@Composable
fun CardSection(content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(vertical = 8.dp), content = content)
    }
}

@Composable
fun ProfileItem(icon: ImageVector, title: String, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(34.dp),
            tint = PrimaryColor
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
        )
    }
}