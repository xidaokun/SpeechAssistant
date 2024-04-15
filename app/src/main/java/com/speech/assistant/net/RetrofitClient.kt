package com.speech.assistant.net

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val HOST = "172.20.10.2:5000"
//    private const val HOST = "10.211.55.4:5000"
    private const val BASE_URL = "http://${HOST}/api/v1/"

    val apiService: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }

}