package com.example.ca3.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.content.Context
import com.example.ca3.data.model.User
import com.example.ca3.data.repository.UserRepository

class UserProfileViewModel : ViewModel() {

    private val _userProfile = MutableLiveData<String>()
    val userProfile: LiveData<String> = _userProfile

    private lateinit var userRepository: UserRepository

    fun init(context: Context) {
        userRepository = UserRepository(context)
        loadUserProfile()
    }

    private fun loadUserProfile() {
        val user = userRepository.getUserProfile()
        _userProfile.value = user?.let {
            "Name: ${it.username}\nAddress: ${it.deliveryAddress}\nPayment: ${it.paymentMethod}"
        } ?: "No profile saved"
    }

    fun saveUserProfile(username: String, address: String, payment: String) {
        userRepository.saveUserProfile(
            User(username, address, payment)
        )
        loadUserProfile()
    }
}
