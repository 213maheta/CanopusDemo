package com.twoonethree.CanopusDemo.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.twoonethree.CanopusDemo.localstorage.User
import com.twoonethree.CanopusDemo.localstorage.dao.UserDao
import kotlinx.coroutines.launch

class AuthViewModel(private val userDao: UserDao) : ViewModel() {
    var message by mutableStateOf("")

    fun registerUser(email: String, password: String, confirmPassword: String) {
        if (!isEmailValid(email)) {
            message = "Invalid email address."
            return
        }
        if (!isPasswordStrong(password)) {
            message = "Password must be at least 8 characters, include a number and a special character."
            return
        }
        if (password != confirmPassword) {
            message = "Passwords do not match."
            return
        }

        viewModelScope.launch {
            userDao.insertUser(User(email, password))
            message = "Registration successful."
        }
    }

    fun loginUser(email: String, password: String, confirmPassword: String) {
        viewModelScope.launch {
            userDao.getUserByEmail(email).collect { user ->
                message = if (user?.password == password) "Login successful." else "Invalid credentials."
            }
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isPasswordStrong(password: String): Boolean {
        return password.length >= 8 && password.any { it.isDigit() } && password.any { !it.isLetterOrDigit() }
    }
}