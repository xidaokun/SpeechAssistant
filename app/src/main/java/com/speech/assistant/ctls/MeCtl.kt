package com.speech.assistant.ctls

import com.speech.assistant.base.base.BaseCtl
import com.speech.assistant.datas.UserInfo
import com.speech.assistant.net.RetrofitClient
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MeCtl : BaseCtl() {

    data class UserData(val status: Int?, val message: String?)

    fun getUserInfo() {
        GlobalScope.launch {
            RetrofitClient.apiService.getUserInfo().run {
                try {
                    val response = execute()
                    if (response.isSuccessful) {
                        val body = response.body()
                        body?.data?.let {
                            // save user info
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun updateUserInfo(userInfo: UserInfo) {
        GlobalScope.launch {
            RetrofitClient.apiService.updateUserInfo(userInfo).run {
                try {
                    val response = execute()
                    if (response.isSuccessful) {
                        val body = response.body()
                        body?.let {
                            // save user info
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

    }
}