package com.example.ca3.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ca3.R
import com.example.ca3.ui.viewmodel.UserProfileViewModel

class UserProfileFragment : Fragment() {

    private lateinit var viewModel: UserProfileViewModel
    private lateinit var usernameInput: EditText
    private lateinit var addressInput: EditText
    private lateinit var paymentInput: EditText
    private lateinit var profileDisplay: TextView
    private lateinit var saveButton: Button
    private lateinit var loadButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_user_profile, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(UserProfileViewModel::class.java)
        viewModel.init(requireContext())

        usernameInput = view.findViewById(R.id.username_input)
        addressInput = view.findViewById(R.id.address_input)
        paymentInput = view.findViewById(R.id.payment_input)
        profileDisplay = view.findViewById(R.id.profile_display)
        saveButton = view.findViewById(R.id.save_profile_btn)
        loadButton = view.findViewById(R.id.load_profile_btn)

        setupListeners()
        observeViewModel()
    }

    private fun setupListeners() {
        saveButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val address = addressInput.text.toString()
            val payment = paymentInput.text.toString()

            if (username.isNotEmpty() && address.isNotEmpty() && payment.isNotEmpty()) {
                viewModel.saveUserProfile(username, address, payment)
                Toast.makeText(requireContext(), "Profile saved!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        loadButton.setOnClickListener {
        }
    }

    private fun observeViewModel() {
        viewModel.userProfile.observe(viewLifecycleOwner) { profile ->
            profileDisplay.text = profile
        }
    }
}
