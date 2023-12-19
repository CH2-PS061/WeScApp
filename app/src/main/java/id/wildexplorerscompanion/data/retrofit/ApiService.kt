package id.wildexplorerscompanion.data.retrofit

import id.wildexplorerscompanion.data.retrofit.response.ChangePasswordResponse
import id.wildexplorerscompanion.data.retrofit.response.DeleteResponse
import id.wildexplorerscompanion.data.retrofit.response.LoginResponse
import id.wildexplorerscompanion.data.retrofit.response.RegisterResponse
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.HTTP
import retrofit2.http.Header
import retrofit2.http.HeaderMap
import retrofit2.http.PATCH
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("users/register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): RegisterResponse

    @FormUrlEncoded
    @POST("users/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): LoginResponse
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "users/delete", hasBody = true)
    suspend fun delete(
        @Header("X-Auth-Token")token: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ): DeleteResponse

    @FormUrlEncoded
    @PATCH("users/change-password")
    suspend fun changePassword(
        @Header("X-Auth-Token")token: String,
        @Field("email") email: String,
        @Field("currentPassword") currentPassword: String,
        @Field("newPassword") newPassword: String
    ): ChangePasswordResponse
}