package com.myprojects.chatroom.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.myprojects.chatroom.Injection
import com.myprojects.chatroom.data.Result
import com.myprojects.chatroom.data.UserRepository
import kotlinx.coroutines.launch

class AuthViewModel: ViewModel() {
    val authInstance = FirebaseAuth.getInstance()
    private val userRepository: UserRepository
    init {
        userRepository = UserRepository(
            authInstance,
            Injection.instance()
        )
    }

    private val _authResult = MutableLiveData<Result<Boolean>>()
    val authResult: LiveData<Result<Boolean>> get() = _authResult

    fun signUp(email: String, password: String, firstName: String, lastName: String) {
        viewModelScope.launch {
            _authResult.value = userRepository.signUp(email, password, firstName, lastName)
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authResult.value = userRepository.login(email, password)
        }
    }
}