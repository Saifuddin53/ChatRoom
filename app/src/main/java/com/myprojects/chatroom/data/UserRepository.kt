package com.myprojects.chatroom.data

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
            auth.createUserWithEmailAndPassword(email, password)
            val user = User(firstName, lastName, email)
            saveUserToFirestore(user)
            Result.Success(true)
        }catch (e: Exception) {
            Result.Error<Exception>(e)
        }


    private suspend fun saveUserToFirestore(user: User) {
        firestore.collection("users").document(user.email).set(user).await()
    }
}