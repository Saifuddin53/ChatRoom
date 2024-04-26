package com.myprojects.chatroom

class Navigation {
}

sealed class Screen(val route: String) {
    object LoginScreen: Screen("loginscreen")
    object SignupScreen: Screen("signupscreen")
    object ChatRoomScreen: Screen("chatroomscreen")
    object ChatScreen: Screen("chatscreen")
}