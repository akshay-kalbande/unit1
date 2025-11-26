package com.example.ca3.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ca3.data.model.Product

class HomeViewModel : ViewModel() {
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products

    init {
        loadProducts()
    }

    private fun loadProducts() {
        _products.value = listOf(
            Product("Shoes", description = "Best shoes", image = "jknkd", price = 1299.0),
            Product("T-Shirt", description = "Best Shirt", image = "jknkd", price = 499.0),
            Product("Jeans", description = "New denim", image = "jknkd", price = 1899.0),
            Product("Jacket", description = "Winter 0 degree ...", image = "jknkd", price = 2499.0),
            Product("Socks", description = "New pair of socks", image = "jknkd", price = 199.0),
            Product("Hat", description = "New winter hat", image = "jknkd", price = 399.0),
            Product("Shoes", description = "Best shoes", image = "jknkd", price = 1299.0),
            Product("Shoes", description = "Best shoes", image = "jknkd", price = 1299.0),
            Product("Shoes", description = "Best shoes", image = "jknkd", price = 1299.0),
            Product("Shoes", description = "Best shoes", image = "jknkd", price = 1299.0),
        )
    }
}
