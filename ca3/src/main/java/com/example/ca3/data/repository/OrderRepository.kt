package com.example.ca3.data.repository

import android.content.Context
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

class OrderRepository(private val context: Context) {

    fun saveOrderToInternalStorage(orderData: String): Boolean {
        return try {
            val file = File(context.filesDir, "orders.txt")
            context.openFileOutput("orders.txt", Context.MODE_APPEND).use { output ->
                output.write("${orderData}\n".toByteArray())
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun readOrdersFromInternalStorage(): String {
        return try {
            val file = File(context.filesDir, "orders.txt")
            if (!file.exists()) return ""
            
            context.openFileInput("orders.txt").use { input ->
                BufferedReader(InputStreamReader(input)).use { reader ->
                    reader.readText()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }

    fun clearOrders(): Boolean {
        return try {
            File(context.filesDir, "orders.txt").delete()
        } catch (e: Exception) {
            false
        }
    }
}
