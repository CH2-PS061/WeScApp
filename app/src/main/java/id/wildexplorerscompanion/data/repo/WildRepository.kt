package id.wildexplorerscompanion.data.repo

import id.wildexplorerscompanion.data.retrofit.ApiService
import id.wildexplorerscompanion.data.retrofit.response.LoginResponse
import id.wildexplorerscompanion.data.retrofit.response.RegisterResponse

class WildRepository private constructor(private val apiService: ApiService ){

    suspend fun register(name: String, email: String, password: String): RegisterResponse {
        return apiService.register(name, email, password)
    }

    suspend fun login(email: String, password: String): LoginResponse {
        return apiService.login(email, password)
    }




    companion object {
        @Volatile
        private var instance: WildRepository? = null
        fun getInstance(
            apiService: ApiService
        ): WildRepository =
            instance ?: synchronized(this){
                instance ?: WildRepository(apiService)
            }.also { instance = it }
    }
}