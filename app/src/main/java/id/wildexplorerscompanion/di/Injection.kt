package id.wildexplorerscompanion.di

import android.content.Context
import id.wildexplorerscompanion.data.repo.WildRepository
import id.wildexplorerscompanion.data.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): WildRepository {
        val apiService = ApiConfig.getApiService()
        return WildRepository.getInstance(apiService)
    }
}