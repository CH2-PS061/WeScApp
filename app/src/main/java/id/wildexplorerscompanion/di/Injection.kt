package id.wildexplorerscompanion.di

import android.content.Context
import id.wildexplorerscompanion.data.pref.UserPreference
import id.wildexplorerscompanion.data.pref.dataStore
import id.wildexplorerscompanion.data.repo.WildRepository
import id.wildexplorerscompanion.data.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): WildRepository {
        val apiService = ApiConfig.getApiService()
        val userPreference = UserPreference.getInstance(context.dataStore)
        return WildRepository.getInstance(apiService, userPreference)
    }
}