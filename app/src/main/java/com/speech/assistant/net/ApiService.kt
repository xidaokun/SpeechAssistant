package com.speech.assistant.net
import com.speech.assistant.datas.LoginInfo
import com.speech.assistant.datas.SResponse
import com.speech.assistant.datas.TransformInfo
import com.speech.assistant.datas.UserInfo
import com.speech.assistant.datas.VoiceInfo
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("auth/login")
    fun login(@Body body: RequestBody): Call<SResponse<LoginInfo>>

    @POST("auth/refresh_token")
    fun refreshToken(): Call<SResponse<LoginInfo>>

    @POST("auth/register")
    fun register(@Body body: RequestBody): Call<SResponse<Any>>

    @POST("auth/verify_code")
    fun verifyCode(@Body body: RequestBody): Call<SResponse<Any>>

    @POST("auth/modify_pwd")
    fun modifyPwd(@Body body: RequestBody): Call<SResponse<Any>>

    @POST("auth/modify_phone")
    fun modifyPhone(@Body body: RequestBody): Call<SResponse<Any>>

    @GET("tss/voice_list")
    fun voiceList(): Call<SResponse<List<VoiceInfo>>>

    @POST("tss/transform")
    fun transform(@Body body: RequestBody): Call<SResponse<TransformInfo>>

    @POST("tss/download_file")
    fun downloadFile(@Body body: RequestBody): Call<ResponseBody>

    @POST("user/get_user")
    fun getUserInfo(): Call<SResponse<UserInfo>>

    @POST("user/update_user")
    fun updateUserInfo(@Body userInfo: UserInfo): Call<SResponse<UserInfo>>


}