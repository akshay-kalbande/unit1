package com.example.ca3.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.ca3.data.model.User

class UserRepository(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun saveUserProfile(user: User) {
        sharedPreferences.edit().apply {
            putString("username", user.username)
            putString("delivery_address", user.deliveryAddress)
            putString("payment_method", user.paymentMethod)
            apply()
        }
    }

    fun getUserProfile(): User? {
        val username = sharedPreferences.getString("username", null) ?: return null
        val address = sharedPreferences.getString("delivery_address", "") ?: ""
        val payment = sharedPreferences.getString("payment_method", "") ?: ""
        
        return User(username, address, payment)
    }
}
