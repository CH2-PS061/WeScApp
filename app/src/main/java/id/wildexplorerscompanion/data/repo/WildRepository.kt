package id.wildexplorerscompanion.data.repo

import android.app.Application
import android.content.Context
import id.wildexplorerscompanion.data.local.room.EdiblePlantsDatabase
import id.wildexplorerscompanion.data.local.room.PlantDao
import id.wildexplorerscompanion.data.pref.UserModel
import id.wildexplorerscompanion.data.pref.UserPreference
import id.wildexplorerscompanion.data.retrofit.ApiService
import id.wildexplorerscompanion.data.retrofit.response.ChangePasswordResponse
import id.wildexplorerscompanion.data.retrofit.response.DeleteResponse
import id.wildexplorerscompanion.data.retrofit.response.LoginResponse
import id.wildexplorerscompanion.data.retrofit.response.RegisterResponse
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class WildRepository private constructor(
    private val apiService: ApiService,
    private val userPreference: UserPreference,
    private val plantDao: PlantDao
){

    suspend fun register(name: String, email: String, password: String): RegisterResponse {
        return apiService.register(name, email, password)
    }

    suspend fun login(email: String, password: String): LoginResponse {
        return apiService.login(email, password)
    }

    suspend fun delete(token: String, email: String, password: String): DeleteResponse {
        return apiService.delete(token, email, password)
    }

    suspend fun changePassword(token: String,email: String,currentPassword: String,newPassword: String): ChangePasswordResponse {
        return apiService.changePassword(token, email, currentPassword, newPassword)
    }


    //User Preferences
    fun getSession(): Flow<UserModel>{
        return userPreference.getSession()
    }

    suspend fun saveSession(user: UserModel){
        userPreference.saveSession(user)
    }
    suspend fun logout() = userPreference.logout()

    //Database
    fun getById(plantId: String) = plantDao.getById(plantId)

    fun getAllPlant() = plantDao.getAllPlant()

    companion object {
        @Volatile
        private var instance: WildRepository? = null
        fun getInstance(
            apiService: ApiService,
            userPreference: UserPreference,
            plantDao: PlantDao
        ): WildRepository =
            instance ?: synchronized(this){
                instance ?: WildRepository(apiService,userPreference,plantDao)
            }.also { instance = it }
    }

}