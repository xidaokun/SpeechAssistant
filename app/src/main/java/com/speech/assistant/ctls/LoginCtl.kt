package com.speech.assistant.ctls

import android.content.ContentValues
import android.util.Log
import com.speech.assistant.MyConstants
import com.speech.assistant.base.DataChangedListener
import com.speech.assistant.base.Settings
import com.speech.assistant.base.base.BaseCtl
import com.speech.assistant.base.utils.PreferenceHelper
import com.speech.assistant.datas.LoginInfo
import com.speech.assistant.datas.SResponse
import com.speech.assistant.net.RetrofitClient
import com.speech.assistant.utls.NetUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginCtl : BaseCtl(){

    private val TAG = "LoginCtl"

    data class LoginData(val status: Int?, val message: String?)

    fun refreshToken(listener: DataChangedListener<LoginData>?) {
        PreferenceHelper.getString(MyConstants.SP_TOKEN_KEY)?.let {
            listener?.onBefore()
            val call = RetrofitClient.apiService.refreshToken()
            call.enqueue(object : Callback<SResponse<LoginInfo>> {
                override fun onResponse(call: Call<SResponse<LoginInfo>>, response: Response<SResponse<LoginInfo>>) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        body?.data?.let { cacheLoginInfo(it) }
                        Log.d(TAG, "onResponse: ${body?.message}")
                        listener?.onChanged(LoginData(body?.status, body?.message))
                        listener?.onAfter(body?.message)
                    }
                }

                override fun onFailure(call: Call<SResponse<LoginInfo>>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                    listener?.onChanged(LoginData(0, t.message))
                    listener?.onAfter(t.message)
                }
            })
        } ?: run {
            listener?.onChanged(LoginData(0, "token is empty!"))
            return
        }
    }


    fun login(username: String, password: String, listener: DataChangedListener<LoginData>) {
        if(username.isEmpty() or password.isEmpty()) {
            listener.onChanged(LoginData(1, "account or password is empty!"))
            return
        }

        listener.onBefore()

        val params = mapOf("name" to username, "password" to password)
        val call = RetrofitClient.apiService.login(NetUtils.createJsonBody(params))
        call.enqueue(object : Callback<SResponse<LoginInfo>> {
            override fun onResponse(call: Call<SResponse<LoginInfo>>, response: Response<SResponse<LoginInfo>>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.data?.let { cacheLoginInfo(it) }
                    Log.d(TAG, "onResponse: ${body?.message}")
                    listener.onChanged(LoginData(body?.status, body?.message))
                    listener.onAfter(body?.message)
                }
            }

            override fun onFailure(call: Call<SResponse<LoginInfo>>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
                listener.onChanged(LoginData(0, t.message))
                listener.onAfter(t.message)
            }
        })
    }

    private fun cacheLoginInfo(loginInfo: LoginInfo) {
        dbHelper.userDao().insertAll(loginInfo)

        PreferenceHelper.save(MyConstants.SP_TOKEN_KEY, loginInfo.access_token)
        PreferenceHelper.save(MyConstants.SP_USERID_KEY, loginInfo.user_id)
        PreferenceHelper.save(MyConstants.SP_USERNAME_KEY, loginInfo.user_name)
    }


}