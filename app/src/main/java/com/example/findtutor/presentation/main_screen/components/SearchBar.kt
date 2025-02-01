package com.example.findtutor.presentation.main_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.findtutor.ui.theme.PrimaryColor
import androidx.navigation.NavController

@Composable
fun LocationSelectionUI(navController: NavController) {
    var showLocationCard by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(16.dp))
                .border(1.dp, PrimaryColor, RoundedCornerShape(16.dp))
                .clickable { showLocationCard = !showLocationCard }
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Location",
                    tint = PrimaryColor,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = "Choose Your Location",
                    style = MaterialTheme.typography.bodyLarge,
                    color = PrimaryColor
                )
            }
        }
        if (showLocationCard) {
            LocationSearchCard(
                searchQuery = searchQuery,
                onSearchQueryChanged = { searchQuery = it },
                onClose = { showLocationCard = false },
                onLocationSelected = { location ->
                    // Navigate to the teacher list screen for the selected location.
                    navController.navigate("teacher_list_screen/$location")
                    showLocationCard = false
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationSearchCard(
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    onClose: () -> Unit,
    onLocationSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .background(Color.White, RoundedCornerShape(16.dp))
            .border(1.dp, Color.LightGray, RoundedCornerShape(16.dp))
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        IconButton(
            onClick = onClose,
            modifier = Modifier.align(Alignment.End)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close",
                tint = Color.Gray
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        TextField(
            value = searchQuery,
            onValueChange = onSearchQueryChanged,
            placeholder = { Text(text = "Search by City, Region, or Area", color = Color.Gray) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = PrimaryColor,
                unfocusedIndicatorColor = Color.LightGray
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Location",
                    tint = PrimaryColor
                )
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        // Option buttons for generic location searches
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            LocationOptionButton(label = "Use My Current Location") { /* Handle current location */ }
            LocationOptionButton(label = "Search by Region") { /* Handle region search */ }
            LocationOptionButton(label = "Search by City") { /* Handle city search */ }
            LocationOptionButton(label = "Search by Area") { /* Handle area search */ }

            // If the user types "Mirpurkhas", show an option to select it directly.
            if (searchQuery.equals("Mirpurkhas", ignoreCase = true)) {
                LocationOptionButton(label = "Mirpurkhas") {
                    onLocationSelected("Mirpurkhas")
                }
            }
        }
    }
}

@Composable
fun LocationOptionButton(label: String, onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.textButtonColors(contentColor = PrimaryColor)
    ) {
        Text(text = label, style = MaterialTheme.typography.bodyMedium)
    }
}
