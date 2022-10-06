package com.develop.submission_intermediate.UI.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.develop.submission_intermediate.Api.LoginResponses
import com.develop.submission_intermediate.Model.UserModell
import com.develop.submission_intermediate.Remote.Repository
import kotlinx.coroutines.launch

class SignInViewModel(private val pref: Repository) : ViewModel() {
    val loginResult: MutableLiveData<LoginResponses.LoginResult?> = pref.loginResult
    val loginResponse: LiveData<LoginResponses.LoginResponse> = pref.loginResponse
    val showLoading: LiveData<Boolean> = pref.showLoading
    val toastText: LiveData<String> = pref.toastText

    fun loginAccount(email: String, password: String){
        viewModelScope.launch {
            pref.loginAccount(email, password)
        }
    }

    fun saveUser(token: UserModell) {
        viewModelScope.launch {
            pref.saveUser(token)
        }
    }

    fun login() {
        viewModelScope.launch {
            pref.login()
        }
    }
}