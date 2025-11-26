package com.example.ca3.data.repository

import android.content.Context
import android.os.Environment
import java.io.File
import java.io.FileWriter

class InvoiceRepository(private val context: Context) {

    fun exportInvoiceToExternalStorage(invoiceContent: String): Boolean {
        return try {
            val documentsDir = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
                "ShopEase"
            )
            
            if (!documentsDir.exists()) {
                documentsDir.mkdirs()
            }

            val timestamp = System.currentTimeMillis()
            val invoiceFile = File(documentsDir, "Invoice_$timestamp.txt")

            FileWriter(invoiceFile).use { writer ->
                writer.write(invoiceContent)
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun isExternalStorageAvailable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }
}
