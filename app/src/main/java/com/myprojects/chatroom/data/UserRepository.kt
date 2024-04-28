package com.myprojects.chatroom.data

import android.content.Context
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.myprojects.chatroom.data.Result.Error
import com.myprojects.chatroom.viewmodel.AuthViewModel
import kotlinx.coroutines.tasks.await

class UserRepository(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
) {
    suspend fun signUp(email: String, password: String, firstName: String, lastName: String): Result<Boolean> =
        try {
            auth.createUserWithEmailAndPassword(email, password).await()
            val user = User(firstName, lastName, email)
            saveUserToFirestore(user)
            Result.Success(true)
        }catch (e: Exception) {
            Result.Error<Exception>(e)
        }

    private suspend fun saveUserToFirestore(user: User) {
        firestore.collection("users").document(user.email).set(user).await()
    }

    suspend fun login(email: String, password: String) =
        try {
            auth.signInWithEmailAndPassword(email, password).await()
            Result.Success(true)
        }catch (e: Exception) {
            Result.Error<Exception>(e)
        }
}
