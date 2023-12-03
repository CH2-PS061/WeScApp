package id.wildexplorerscompanion.data.repo

import id.wildexplorerscompanion.data.pref.UserModel
import id.wildexplorerscompanion.data.pref.UserPreference
import id.wildexplorerscompanion.data.retrofit.ApiService
import id.wildexplorerscompanion.data.retrofit.response.LoginResponse
import id.wildexplorerscompanion.data.retrofit.response.RegisterResponse
import kotlinx.coroutines.flow.Flow

class WildRepository private constructor(private val apiService: ApiService,private val userPreference: UserPreference){

    suspend fun register(name: String, email: String, password: String): RegisterResponse {
        return apiService.register(name, email, password)
    }

    suspend fun login(email: String, password: String): LoginResponse {
        return apiService.login(email, password)
    }


    //User Preferences
    fun getSession(): Flow<UserModel>{
        return userPreference.getSession()
    }

    suspend fun saveSession(user: UserModel){
        userPreference.saveSession(user)
    }

    suspend fun logout() = userPreference.logout()

    companion object {
        @Volatile
        private var instance: WildRepository? = null
        fun getInstance(
            apiService: ApiService,
            userPreference: UserPreference
        ): WildRepository =
            instance ?: synchronized(this){
                instance ?: WildRepository(apiService,userPreference)
            }.also { instance = it }
    }

}