package com.develop.submission_intermediate.UI.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.develop.submission_intermediate.MainActivity
import com.develop.submission_intermediate.R
import com.develop.submission_intermediate.UI.SignUp.SignUpFragment
import com.develop.submission_intermediate.databinding.FragmenttSignInBinding



class SignInFragment : Fragment() {

    private var _binding: FragmenttSignInBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmenttSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAction()
    }

    private fun setupAction() {
        binding.apply{
            btnBack.setOnClickListener{
                val intent = Intent(this@SignInFragment.requireContext(), MainActivity::class.java)
                startActivity(intent)
            }

            btnRegister.setOnClickListener{
             val signUpFragment = SignUpFragment()
                val mFragmentManager = parentFragmentManager
                mFragmentManager.beginTransaction().apply {
                    replace(R.id.signInIn, signUpFragment, SignUpFragment::class.java.simpleName)
                    addToBackStack(null)
                    commit()
                }
                binding.btnRegister.visibility = View.GONE
                binding.btnForgetPassword.visibility = View.GONE
                binding.btnSignIn.visibility = View.GONE

            }
        }
    }




}

