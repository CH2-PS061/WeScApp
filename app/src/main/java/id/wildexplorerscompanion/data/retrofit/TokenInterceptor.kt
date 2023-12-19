package id.wildexplorerscompanion.data.retrofit

import id.wildexplorerscompanion.ui.login.LoginViewModel
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
class TokenInterceptor() : Interceptor {
    var tokenValue = ""
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        // Extract the token from the response headers )
        val token = response.headers("x-auth-token: ")
        tokenValue = token.toString()
        return response
    }
}

