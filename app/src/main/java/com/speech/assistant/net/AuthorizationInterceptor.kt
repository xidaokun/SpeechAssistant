package com.speech.assistant.net

import com.speech.assistant.MyConstants
import com.speech.assistant.base.utils.PreferenceHelper
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = PreferenceHelper.getString(MyConstants.SP_TOKEN_KEY)
        val request = chain.request()
        val newRequest = request.newBuilder()
            .addHeader("Authorization", "Bearer ${token ?: ""}")
            .build()
        return chain.proceed(newRequest)
    }
}