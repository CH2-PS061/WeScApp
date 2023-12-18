package id.wildexplorerscompanion.di

import android.app.Application
import android.content.Context
import id.wildexplorerscompanion.data.local.room.EdiblePlantsDatabase
import id.wildexplorerscompanion.data.pref.UserPreference
import id.wildexplorerscompanion.data.pref.dataStore
import id.wildexplorerscompanion.data.repo.WildRepository
import id.wildexplorerscompanion.data.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): WildRepository {
        val apiService = ApiConfig.getApiService()
        val userPreference = UserPreference.getInstance(context.dataStore)
        val database= EdiblePlantsDatabase.getDatabase(context)
        val dao = database.plantDao()
        return WildRepository.getInstance(apiService, userPreference,dao)
    }
}