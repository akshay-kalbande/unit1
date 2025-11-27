package com.example.ca3.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.content.Context
import com.example.ca3.data.model.Product
import com.example.ca3.data.repository.UserRepository

class CartViewModel : ViewModel() {
    private val _cartItems = MutableLiveData<List<Product>>()
    val cartItems: LiveData<List<Product>> = _cartItems

//    private lateinit var cartRepository: UserRepository

    fun init(context: Context) {
//        userRepository = cartRepository(context)
        loadCartItems()
    }

    private fun loadCartItems() {
    }

    fun saveItemToCartProfile(username: String, address: String, payment: String) {
    }

    fun updateCartItems(items: List<Product>) {
        _cartItems.value = items
    }
}
