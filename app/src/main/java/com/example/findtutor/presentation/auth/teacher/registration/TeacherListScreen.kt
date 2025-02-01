package com.example.findtutor.presentation.auth.teacher.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.findtutor.ui.theme.PrimaryColor
import com.example.findtutor.ui.theme.poppinsBold
import com.example.findtutor.ui.theme.poppinsRegular

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeacherListScreen(navController: NavController, location: String) {
    // Define a list of teachers (including some from Mirpurkhas).
    val allTeachers = listOf(
        Teacher("Ali Khan", "MSc Mathematics", "Math, Algebra, Geometry", "5", "20000", "High School", "Mirpurkhas"),
        Teacher("Hassan Ali", "MSc Mathematics", "Math, Calculus, Algebra", "4", "21000", "High School", "Mirpurkhas"),
        Teacher("Sana Bibi", "MA Mathematics", "Math, Statistics, Geometry", "6", "19000", "Secondary", "Mirpurkhas"),
        Teacher("Rashid Khan", "MSc Mathematics", "Math, Trigonometry, Algebra", "7", "23000", "High School", "Mirpurkhas"),
        Teacher("Zara Iqbal", "BSc Mathematics", "Math, Algebra, Calculus", "5", "20000", "Primary", "Mirpurkhas"),
        Teacher("Ayesha Raza", "MA English", "English, Grammar, Literature", "3", "18000", "Secondary", "Lahore")
    )

    // Filter the list to only include teachers from the selected location.
    val filteredTeachers = allTeachers.filter { it.area.equals(location, ignoreCase = true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Available Tutors in $location",
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
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .background(Color.White)
        ) {
            items(filteredTeachers) { teacher ->
                TeacherListItem(teacher)
            }
        }
    }
}

@Composable
fun TeacherListItem(teacher: Teacher) {
    var showDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable { showDialog = true },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.AccountCircle,
                contentDescription = "Profile Picture",
                tint = PrimaryColor,
                modifier = Modifier
                    .size(60.dp)
                    .padding(end = 12.dp)
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = teacher.name,
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontFamily = poppinsBold
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${teacher.qualification} • ${teacher.category}",
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = Color.Gray,
                        fontFamily = poppinsRegular
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = teacher.subjects,
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = Color.Gray,
                        fontFamily = poppinsRegular
                    ),
                    maxLines = 1,
                    softWrap = false
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Experience: ${teacher.experience} yrs • Rs. ${teacher.fee}",
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = PrimaryColor,
                        fontFamily = poppinsBold
                    )
                )
            }
        }
    }

    if (showDialog) {
        TeacherInfoDialog(teacher, onDismiss = { showDialog = false })
    }
}

@Composable
fun TeacherInfoDialog(teacher: Teacher, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(12.dp),
        title = {
            Text(
                text = teacher.name,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = poppinsBold,
                    color = Color.Black
                )
            )
        },
        text = {
            Column {
                InfoItem("Qualification:", teacher.qualification)
                InfoItem("Subjects:", teacher.subjects)
                InfoItem("Experience:", "${teacher.experience} years")
                InfoItem("Fee:", "Rs. ${teacher.fee}")
                InfoItem("Category:", teacher.category)
                InfoItem("Area:", teacher.area)
            }
        },
        confirmButton = {
            TextButton(
                onClick = onDismiss,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = PrimaryColor
                )
            ) {
                Text(
                    "Close",
                    style = TextStyle(fontFamily = poppinsBold)
                )
            }
        }
    )
}

@Composable
fun InfoItem(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(
            text = label,
            style = TextStyle(
                fontSize = 12.sp,
                color = Color.Gray,
                fontFamily = poppinsRegular
            )
        )
        Text(
            text = value,
            style = TextStyle(
                fontSize = 14.sp,
                color = Color.Black,
                fontFamily = poppinsRegular
            ),
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}


data class Teacher(
    val name: String,
    val qualification: String,
    val subjects: String,
    val experience: String,
    val fee: String,
    val category: String, // e.g., Primary, Secondary, High School
    val area: String      // e.g., Mirpurkhas
)

