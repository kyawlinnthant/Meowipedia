package com.everest.network

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder().addHeader(
            "X-API-KEY",
            "live_Io23GcqdrbaIOcBboiB2ZvKGkoMAPSqEm3sq8FkhQfK5c3aP2Njy5zXNf7AzF3NO"
        )
        return chain.proceed(builder.build())
    }
}