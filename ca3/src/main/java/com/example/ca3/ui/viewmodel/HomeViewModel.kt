package com.example.ca3.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    private val _products = MutableLiveData<List<String>>()
    val products: LiveData<List<String>> = _products

    init {
        loadProducts()
    }

    private fun loadProducts() {
        _products.value = listOf(
            "Shoes - ₹1299",
            "T-Shirt - ₹499",
            "Jeans - ₹1899",
            "Jacket - ₹2499",
            "Socks - ₹199",
            "Hat - ₹399"
        )
    }
}
