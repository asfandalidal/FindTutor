package com.example.findtutor.utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.findtutor.R
import com.example.findtutor.ui.theme.PrimaryColor

@Composable
fun ExitConfirmationDialog(
    showDialog: MutableState<Boolean>,
    onExit: () -> Unit
) {
    val customFontFamily = FontFamily(
        Font(R.font.poppins_regular)
    )

    if (showDialog.value) {
        Dialog(
            onDismissRequest = { showDialog.value = false },
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = false)
        ) {
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .background(Color.Transparent),
                elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    PrimaryColor.copy(alpha = 0.2f),
                                    Color.White
                                )
                            )
                        )
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Title
                    Text(
                        text = "Confirm Exit",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(vertical = 8.dp),
                        fontFamily = customFontFamily
                    )

                    // Message
                    Text(
                        text = "Are you sure you want to exit the app?",
                        fontSize = 16.sp,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 16.dp),
                        textAlign = TextAlign.Center,
                        fontFamily = customFontFamily
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Buttons Row
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(
                            onClick = {
                                showDialog.value = false
                                onExit()
                            },
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 8.dp),
                            border = BorderStroke(width = 1.dp, color = PrimaryColor),
                            shape = RoundedCornerShape(20),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                        ) {
                            Text(
                                "Yes",
                                color = PrimaryColor,
                                fontWeight = FontWeight.Bold,
                                fontFamily = customFontFamily
                            )
                        }
                        Button(
                            onClick = { showDialog.value = false },
                            shape = RoundedCornerShape(20),
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 8.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor)
                        ) {
                            Text(
                                "Cancel", color = Color.White, fontWeight = FontWeight.Bold,
                                fontFamily = customFontFamily
                            )
                        }
                    }
                }
            }
        }
    }
}
