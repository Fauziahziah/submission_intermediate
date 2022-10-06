package com.develop.submission_intermediate.Remote


import android.content.Context
import androidx.datastore.core.DataStore

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.develop.submission_intermediate.Api.ApiConfig
import com.develop.submission_intermediate.Model.UserPreference

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("token")


object Injection {
    fun provideRepository(context: Context): Repository {
        val pref = UserPreference.getInstance(context.dataStore)
        val apiService = ApiConfig.getApiService()
        return Repository.getInstance(pref, apiService)
    }

}