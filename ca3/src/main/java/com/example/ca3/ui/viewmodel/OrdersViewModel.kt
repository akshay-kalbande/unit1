package com.example.ca3.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.content.Context
import com.example.ca3.data.repository.OrderRepository
import com.example.ca3.data.repository.InvoiceRepository

class OrdersViewModel : ViewModel() {
    private val _orderHistory = MutableLiveData<String>()
    val orderHistory: LiveData<String> = _orderHistory

    private val _invoiceStatus = MutableLiveData<String>()
    val invoiceStatus: LiveData<String> = _invoiceStatus

    private lateinit var orderRepository: OrderRepository
    private lateinit var invoiceRepository: InvoiceRepository

    fun init(context: Context) {
        orderRepository = OrderRepository(context)
        invoiceRepository = InvoiceRepository(context)
        loadOrders()
    }

    fun saveOrder(orderData: String) {
        if (orderRepository.saveOrderToInternalStorage(orderData)) {
            loadOrders()
        }
    }

    private fun loadOrders() {
        val orders = orderRepository.readOrdersFromInternalStorage()
        _orderHistory.value = orders.ifEmpty { "No orders yet" }
    }

    fun exportInvoice(invoiceContent: String): Boolean {
        val success = invoiceRepository.exportInvoiceToExternalStorage(invoiceContent)
        _invoiceStatus.value = if (success) "Invoice exported successfully!" else "Failed to export"
        return success
    }

    fun isStorageAvailable(): Boolean {
        return invoiceRepository.isExternalStorageAvailable()
    }

    fun clearOrders() {
        orderRepository.clearOrders()
        loadOrders()
    }
}
