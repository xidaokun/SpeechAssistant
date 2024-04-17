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
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class LoginCtl : BaseCtl(){

    private val TAG = "LoginCtl"

    data class LoginData(val status: Int?, val message: String?)

    @OptIn(DelicateCoroutinesApi::class)
    fun refreshToken(listener: DataChangedListener<LoginData>?) {
        listener?.onBefore()
        GlobalScope.launch {
            PreferenceHelper.getString(MyConstants.SP_TOKEN_KEY)?.let {
                RetrofitClient.apiService.refreshToken().run {
                    try {
                        val call = execute()
                        if (call.isSuccessful) {
                            val body = call.body()
                            body?.data?.let { cacheLoginInfo(it) }
                            Log.d(TAG, "onResponse: ${body?.message}")
                            withContext(Dispatchers.Main) {
                                listener?.onChanged(LoginData(body?.status, body?.message))
                                listener?.onAfter(body?.message)
                            }
                        } else {
                            Log.d(TAG, "onFailure: ${call.message()}")
                            withContext(Dispatchers.Main) {
                                listener?.onChanged(LoginData(0, call.message()))
                                listener?.onAfter(call.message())
                            }
                        }
                    } catch (e : Exception) {
                        Log.d(TAG, "onFailure: ${e.message}")
                        withContext(Dispatchers.Main) {
                            listener?.onChanged(LoginData(0, e.message))
                            listener?.onAfter(e.message)
                        }
                    }
                }
            } ?: run {
                withContext(Dispatchers.Main) {
                    listener?.onChanged(LoginData(0, "token is empty!"))
                    listener?.onAfter("token is empty!")
                }
            }
        }
    }


    @OptIn(DelicateCoroutinesApi::class)
    fun login(username: String, password: String, listener: DataChangedListener<LoginData>) {
        if (username.isEmpty() or password.isEmpty()) {
            listener.onChanged(LoginData(1, "account or password is empty!"))
            return
        }
        listener.onBefore()

        GlobalScope.launch {
            val params = mapOf("name" to username, "password" to password)
            RetrofitClient.apiService.login(NetUtils.createJsonBody(params)).run {
                try {
                    val response = execute()
                    if (response.isSuccessful) {
                        val body = response.body()
                        Log.d(TAG, "onResponse: ${body?.message}")
                        body?.data?.let { cacheLoginInfo(it) }
                        withContext(Dispatchers.Main) {
                            listener.onChanged(LoginData(body?.status, body?.message))
                            listener.onAfter(body?.message)
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            listener.onChanged(LoginData(0, response.message()))
                            listener.onAfter(response.message())
                        }
                    }
                } catch (e : Exception) {
                    Log.d(TAG, "onFailure: ${e.message}")
                    withContext(Dispatchers.Main) {
                        listener.onChanged(LoginData(0, e.message))
                        listener.onAfter(e.message)
                    }
                }
            }
        }
    }

    private fun cacheLoginInfo(loginInfo: LoginInfo) {
        dbHelper.userDao().insertAll(loginInfo)

        PreferenceHelper.save(MyConstants.SP_TOKEN_KEY, loginInfo.access_token)
        PreferenceHelper.save(MyConstants.SP_USERID_KEY, loginInfo.user_id)
        PreferenceHelper.save(MyConstants.SP_USERNAME_KEY, loginInfo.user_name)
    }
}