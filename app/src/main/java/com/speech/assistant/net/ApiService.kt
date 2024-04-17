package com.speech.assistant.net
import com.speech.assistant.datas.LoginInfo
import com.speech.assistant.datas.SResponse
import com.speech.assistant.datas.TransformInfo
import com.speech.assistant.datas.UserInfo
import com.speech.assistant.datas.VoiceInfo
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("user/login")
    fun login(@Body body: RequestBody): Call<SResponse<LoginInfo>>

    @POST("user/refresh_token")
    fun refreshToken(): Call<SResponse<LoginInfo>>

    @POST("user/register")
    fun register(@Body body: RequestBody): Call<SResponse<Any>>

    @POST("user/verify_code")
    fun verifyCode(@Body body: RequestBody): Call<SResponse<Any>>

    @POST("user/modify_pwd")
    fun modifyPwd(@Body body: RequestBody): Call<SResponse<Any>>

    @POST("user/modify_phone")
    fun modifyPhone(@Body body: RequestBody): Call<SResponse<Any>>

    @GET("tss/voice_list")
    fun voiceList(): Call<SResponse<List<VoiceInfo>>>

    @POST("tss/transform")
    fun transform(@Body body: RequestBody): Call<SResponse<TransformInfo>>

    @POST("user/user_info")
    fun getUserInfo(@Body body: RequestBody): Call<SResponse<UserInfo>>

    @POST("user/update_user_info")
    fun updateUserInfo(@Body body: RequestBody): Call<SResponse<Any>>


}