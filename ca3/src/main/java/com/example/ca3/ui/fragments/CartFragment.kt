package com.example.ca3.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ca3.R
import com.example.ca3.ui.viewmodel.CartViewModel

class CartFragment : Fragment() {

    private lateinit var viewModel: CartViewModel
    private lateinit var cartDisplay: TextView
    private lateinit var saveButton: Button
    private lateinit var loadButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_cart, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        viewModel.init(requireContext())
        cartDisplay = view.findViewById(R.id.profile_display)
        saveButton = view.findViewById(R.id.save_profile_btn)
        loadButton = view.findViewById(R.id.load_profile_btn)

        setupListeners()
        observeViewModel()
    }

    private fun setupListeners() {
        saveButton.setOnClickListener {

//            if (username.isNotEmpty() && address.isNotEmpty() && payment.isNotEmpty()) {
//                viewModel.saveUserProfile(username, address, payment)
//                Toast.makeText(requireContext(), "Profile saved!", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(requireContext(), "Fill all fields", Toast.LENGTH_SHORT).show()
//            }
        }

        loadButton.setOnClickListener {
        }
    }

    private fun observeViewModel() {
        viewModel.cartItems.observe(viewLifecycleOwner) { cartItems ->
//            cartDisplay.text = cartItems.map {
//                ""
//            }
        }
    }
}
