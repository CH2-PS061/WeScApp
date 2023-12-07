package id.wildexplorerscompanion.data.retrofit

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class TokenInterceptor: Interceptor {
    var authToken: String? = null


    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request =chain.request()

        val response: Response = chain.proceed(originalRequest)

        val authHeader = response.header("X-Auth-Token")

        if (authHeader != null && authHeader.startsWith("")) {
            authToken = authHeader.substring("".length)
        }

        return response
    }

}