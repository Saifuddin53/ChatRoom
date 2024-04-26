package com.myprojects.chatroom

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.myprojects.chatroom.screen.LoginScreen
import com.myprojects.chatroom.screen.SignUpScreen

@Composable
fun Navigation(
    navController: NavHostController,
) {
    NavHost(navController = navController,
        startDestination = Screen.SignupScreen.route) {
        composable(route = Screen.SignupScreen.route) {
            SignUpScreen{
                navController.navigate(route = Screen.LoginScreen.route)
            }
        }
        composable(route = Screen.LoginScreen.route) {
            LoginScreen {
                navController.navigate(route = Screen.SignupScreen.route)
            }
        }
    }
}

sealed class Screen(val route: String) {
    object LoginScreen: Screen("loginscreen")
    object SignupScreen: Screen("signupscreen")
    object ChatRoomScreen: Screen("chatroomscreen")
    object ChatScreen: Screen("chatscreen")
}