package com.everest.network

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder().addHeader(
            "x-api-key",
            BuildConfig.API_KEY
        )
        return chain.proceed(builder.build())
    }
}
