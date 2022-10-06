package com.develop.submission_intermediate.UI.SignUp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.develop.submission_intermediate.Api.RegisterResponse
import com.develop.submission_intermediate.Remote.Repository
import kotlinx.coroutines.launch

class SignUpViewModel(private val pref: Repository) : ViewModel() {
    val registerResponse: LiveData<RegisterResponse> = pref.registerResponse
    val toastText: LiveData<String> = pref.toastText
    val showLoading: LiveData<Boolean> = pref.showLoading

    fun registerAccount(name: String, email: String, password: String) {
        viewModelScope.launch {
            pref.registerAccount(name, email, password)
        }
    }
}