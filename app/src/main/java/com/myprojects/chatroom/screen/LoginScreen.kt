package com.myprojects.chatroom.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        var email by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }

        OutlinedTextField(value = email,
            onValueChange = {email = it},
            label = {
                Text(text = "Email")
            },
            modifier = Modifier
                .padding(bottom = 8.dp, start = 24.dp, end = 24.dp)
                .fillMaxWidth())
        OutlinedTextField(value = password,
            onValueChange = {password = it},
            label = {
                Text(text = "Password")
            },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .padding(bottom = 8.dp, start = 24.dp, end = 24.dp)
                .fillMaxWidth())

        Button(onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp)) {
            Text(text = "Login")
        }

        Text(text = "Don't have an account? Sign up",
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 8.dp)
                .clickable { })
    }
}