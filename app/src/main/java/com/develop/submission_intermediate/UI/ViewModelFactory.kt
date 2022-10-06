package com.develop.submission_intermediate.UI

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.develop.submission_intermediate.Remote.Injection
import com.develop.submission_intermediate.Remote.Repository
import com.develop.submission_intermediate.UI.SignUp.SignUpFragment
import com.develop.submission_intermediate.UI.SignUp.SignUpViewModel
import com.develop.submission_intermediate.UI.login.SignInViewModel

class ViewModelFactory(private val pref: Repository) : ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SignUpViewModel::class.java) -> {
                SignUpViewModel(pref) as T
            }
            modelClass.isAssignableFrom(SignInViewModel::class.java) -> {
                SignInViewModel(pref) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory {
            return instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
        }
    }

}