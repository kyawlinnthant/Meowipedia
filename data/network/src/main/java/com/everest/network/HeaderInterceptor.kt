package com.everest.network

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    val token = BuildConfig.API_KEY
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder().addHeader(
            "X-API-KEY",
            token
        )
        return chain.proceed(builder.build())
    }
}
