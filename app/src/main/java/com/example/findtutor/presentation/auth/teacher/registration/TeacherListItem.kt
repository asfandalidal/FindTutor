package com.example.findtutor.presentation.auth.teacher.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.findtutor.ui.theme.PrimaryColor
import com.example.findtutor.ui.theme.poppinsBold
import com.example.findtutor.ui.theme.poppinsRegular

@Composable
fun TeacherListItem(
    name: String,
    qualification: String,
    subjects: String,
    experience: String,
    fee: String,
    imageUrl: Int? = null
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Profile Image
            if (imageUrl != null) {
                Image(
                    painter = painterResource(id = imageUrl),
                    contentDescription = "Profile Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(60.dp)
                        .padding(end = 12.dp)
                        .clip(CircleShape)
                )
            } else {
                Icon(
                    imageVector = Icons.Outlined.AccountCircle,
                    contentDescription = "Default Profile",
                    modifier = Modifier
                        .size(60.dp)
                        .padding(end = 12.dp),
                    tint = PrimaryColor
                )
            }

            // Teacher Information
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = name,
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontFamily = poppinsBold
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = qualification,
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = Color.Gray,
                        fontFamily = poppinsRegular
                    )
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Subjects: $subjects",
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = Color.Gray,
                        fontFamily = poppinsRegular
                    )
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Experience: $experience years",
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = Color.Gray,
                        fontFamily = poppinsRegular
                    )
                )
            }

            // Fee Section
            Text(
                text = "Rs. $fee",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = PrimaryColor,
                    fontFamily = poppinsBold
                ),
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}