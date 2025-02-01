package com.example.findtutor.presentation.auth.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.findtutor.ui.theme.Error
import com.example.findtutor.ui.theme.PrimaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    isValid: Boolean,
    hint: String,
    keyboardOptions: KeyboardOptions,
    errorBorderColor: Color = Error,
    modifier: Modifier
) {
    OutlinedTextField(
        value = value,
        placeholder = { Text(text = hint, color = Color.White) },
        onValueChange = onValueChange,
        isError = !isValid,
        keyboardOptions = keyboardOptions,
        modifier = modifier
            .fillMaxWidth()
            .background(PrimaryColor)
            .border(2.dp, Color.White, shape = RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp)),
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.White,
            errorBorderColor = errorBorderColor,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            cursorColor = Color.White
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomPasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    isValid: Boolean,
    hint: String,
    keyboardOptions: KeyboardOptions,
    passwordVisible: Boolean,
    onVisibilityToggle: () -> Unit,
    errorBorderColor: Color = Error,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        isError = !isValid,
        keyboardOptions = keyboardOptions,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        modifier = modifier
            .fillMaxWidth()
            .background(PrimaryColor)
            .border(2.dp, Color.White, shape = RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp)),
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.White,
            errorBorderColor = errorBorderColor,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            cursorColor = Color.White
        ),
        placeholder = { Text(text = hint, color = Color.White) },
        trailingIcon = {
            IconButton(onClick = onVisibilityToggle) {
                val icon =
                    if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                Icon(icon, contentDescription = null, tint = Color.White)
            }
        }
    )
}