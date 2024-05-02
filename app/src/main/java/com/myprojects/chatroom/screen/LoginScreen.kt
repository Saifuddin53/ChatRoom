package com.myprojects.chatroom.screen

import android.util.Log
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myprojects.chatroom.data.Result
import com.myprojects.chatroom.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    onSignInSuccess: () -> Unit,
    onNavigateToSignUp: () -> Unit
) {
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

        val result by authViewModel.authResult.observeAsState(initial = Result.Success(false))

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

        Button(onClick = {
            authViewModel.login(email, password)
//            email = ""
//            password = ""
            when(result) {
                is Result.Success -> {
                    onSignInSuccess()
                }
                is Result.Error<*> -> {
                    Log.d("E", "Error messgae")
                }
            }
        },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp)) {
            Text(text = "Login")
        }

        Text(text = "Don't have an account? Sign up",
            fontSize = 14.sp,
            modifier = Modifier
                .padding(top = 8.dp)
                .clickable { onNavigateToSignUp() })
    }
}