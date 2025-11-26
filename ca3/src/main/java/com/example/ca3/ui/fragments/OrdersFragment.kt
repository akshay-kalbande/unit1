package com.example.ca3.ui.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ca3.R
import com.example.ca3.ui.viewmodel.OrdersViewModel

class OrdersFragment : Fragment() {

    private lateinit var viewModel: OrdersViewModel
    private lateinit var orderInput: EditText
    private lateinit var orderHistory: TextView
    private lateinit var invoiceOutput: TextView
    private lateinit var saveOrderBtn: Button
    private lateinit var readOrderBtn: Button
    private lateinit var exportInvoiceBtn: Button

    private val PERMISSION_REQUEST_CODE = 100

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_orders, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(OrdersViewModel::class.java)
        viewModel.init(requireContext())

        orderInput = view.findViewById(R.id.order_input)
        orderHistory = view.findViewById(R.id.order_history)
        invoiceOutput = view.findViewById(R.id.invoice_output)
        saveOrderBtn = view.findViewById(R.id.save_order_btn)
        readOrderBtn = view.findViewById(R.id.read_order_btn)
        exportInvoiceBtn = view.findViewById(R.id.export_invoice_btn)

        setupListeners()
        observeViewModel()
    }

    private fun setupListeners() {
        saveOrderBtn.setOnClickListener {
            val orderData = orderInput.text.toString()
            if (orderData.isNotEmpty()) {
                viewModel.saveOrder(orderData)
                orderInput.text.clear()
                Toast.makeText(requireContext(), "Order saved!", Toast.LENGTH_SHORT).show()
            }
        }

        readOrderBtn.setOnClickListener {}

        exportInvoiceBtn.setOnClickListener {
            if (checkAndRequestPermissions()) {
                val invoice = orderHistory.text.toString()
                if (invoice.isNotEmpty()) {
                    viewModel.exportInvoice(invoice)
                } else {
                    Toast.makeText(requireContext(), "No orders to export", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun checkAndRequestPermissions(): Boolean {
        return if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            true
        } else {
            requestPermissions(
                arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ),
                PERMISSION_REQUEST_CODE
            )
            false
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val invoice = orderHistory.text.toString()
                viewModel.exportInvoice(invoice)
            }
        }
    }

    private fun observeViewModel() {
        viewModel.orderHistory.observe(viewLifecycleOwner) { history ->
            orderHistory.text = history
        }

        viewModel.invoiceStatus.observe(viewLifecycleOwner) { status ->
            Toast.makeText(requireContext(), status, Toast.LENGTH_SHORT).show()
        }
    }
}
