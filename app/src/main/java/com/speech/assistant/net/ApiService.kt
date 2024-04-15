package com.speech.assistant.net
import com.speech.assistant.datas.SResponse
import com.speech.assistant.datas.TransformInfo
import com.speech.assistant.datas.UserInfo
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @POST("user/login")
    fun login(@Body body: RequestBody): Call<SResponse<Any>>

    @POST("user/register")
    fun register(@Body body: RequestBody): Call<SResponse<Any>>

    @POST("user/verify_code")
    fun verifyCode(@Body body: RequestBody): Call<SResponse<Any>>

    @POST("user/modify_pwd")
    fun modifyPwd(@Body body: RequestBody): Call<SResponse<Any>>

    @POST("user/modify_phone")
    fun modifyPhone(@Body body: RequestBody): Call<SResponse<Any>>

    @POST("tss/voice_list")
    fun voiceList(@Body body: RequestBody): Call<ResponseBody>

    @POST("tss/transform")
    fun transform(@Body body: RequestBody): Call<SResponse<TransformInfo>>

    @POST("user/user_info")
    fun getUserInfo(@Body body: RequestBody): Call<SResponse<UserInfo>>

    @POST("user/update_user_info")
    fun updateUserInfo(@Body body: RequestBody): Call<SResponse<Any>>


}