package com.develop.submission_intermediate.UI.SignUp

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.viewModelFactory
import com.develop.submission_intermediate.MainActivity
import com.develop.submission_intermediate.R
import com.develop.submission_intermediate.UI.ViewModelFactory
import com.develop.submission_intermediate.UI.login.SignInFragment
import com.develop.submission_intermediate.databinding.FragmenttSignOutBinding


class SignUpFragment : Fragment() {

    private var _binding: FragmenttSignOutBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModelFactory: ViewModelFactory
    private val signUpViewModel: SignUpViewModel by viewModels { viewModelFactory }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       _binding = FragmenttSignOutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAction()
        setupViewModel()
        setActionSignUp()

    }

    private fun setupAction() {
        binding.apply {
            btnBack.setOnClickListener{
                val intent = Intent(this@SignUpFragment.requireContext(), MainActivity::class.java)
                startActivity(intent)
            }
            btnSignIn.setOnClickListener{
                val signInFragment = SignInFragment()
                val mFragmentManager = parentFragmentManager
                mFragmentManager.beginTransaction().apply {
                    replace(R.id.SignUpUp, signInFragment, SignInFragment::class.java.simpleName)
                    addToBackStack(null)
                    commit()
                }
                binding.btnSignUp.visibility = View.GONE
                binding.btnSignIn.visibility = View.GONE

            }


        }
    }


    private fun setupViewModel() {
        viewModelFactory = ViewModelFactory.getInstance(requireActivity())

    }

    private fun setActionSignUp() {
        binding.btnSignUp.setOnClickListener {
            val email = binding.etEmailEdit.text.toString()
            val username = binding.etUsernameEdit.text.toString()
            val password = binding.etPasswordEdit.text.toString()

            when {
                email.isEmpty() -> {
                    binding.etUsernameEdit.error = getString(R.string.message_name_error)
                }
                username.isEmpty() -> {
                    binding.etEmailEdit.error = getString(R.string.message_email_error)
                }
                password.isEmpty() -> {
                    binding.etPasswordEdit.error = getString(R.string.message_password_error)
                }

                else -> {
                    setId()
                    showToast()
                    intentFragment()
                }
            }
        }
    }

    private fun intentFragment() {
        signUpViewModel.registerResponse.observe( viewLifecycleOwner) { response ->
            if (!response.error) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.SignUpUp, SignInFragment())
                    .commit()
                }
                binding.btnSignUp.visibility = View.GONE
                binding.btnSignIn.visibility = View.GONE
            }
        }


    private fun setId() {
        binding.apply {
            signUpViewModel.registerAccount(
                etEmailEdit.text.toString(),
                etUsernameEdit.text.toString(),
                etPasswordEdit.text.toString()
            )
        }
    }

    private fun showToast() {
        signUpViewModel.toastText.observe(viewLifecycleOwner) { toastText ->
            Toast.makeText(activity, toastText, Toast.LENGTH_SHORT
            ).show()
        }
    }

}


