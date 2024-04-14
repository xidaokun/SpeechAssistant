package com.speech.assistant.net
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("login")
    fun login(@Body body: RequestBody): Call<ResponseBody>

    @FormUrlEncoded
    @POST("register")
    fun register(@Body body: RequestBody): Call<ResponseBody>

    fun verifyCode(@Body body: RequestBody): Call<ResponseBody>

    fun modifyPwd(@Body body: RequestBody): Call<ResponseBody>

    fun modifyPhone(@Body body: RequestBody): Call<ResponseBody>

    fun voiceList(@Body body: RequestBody): Call<ResponseBody>

    fun transform(@Body body: RequestBody): Call<ResponseBody>

    fun getUserInfo(@Body body: RequestBody): Call<ResponseBody>

    fun updateUserInfo(@Body body: RequestBody): Call<ResponseBody>


}