package com.speech.assistant.ctls

import android.util.Log
import com.speech.assistant.base.DataChangedListener
import com.speech.assistant.base.base.BaseCtl
import com.speech.assistant.datas.SResponse
import com.speech.assistant.datas.TransformInfo
import com.speech.assistant.datas.VoiceInfo
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

class WriteCtl : BaseCtl() {

    private val TAG = "WriteCtl"

    data class VoiceData(val status: Int?, val message: String?, val data: List<VoiceInfo>? = null)

    data class WriteData(val status: Int?, val message: String?)

    @OptIn(DelicateCoroutinesApi::class)
    fun getVoiceList(listener: DataChangedListener<VoiceData>) {
        GlobalScope.launch {
            dbHelper.voiceDao().getAll().let {
                if (it.isNotEmpty()) {
                    listener.onChanged(VoiceData(1, "success", it))
                } else {
                    RetrofitClient.apiService.voiceList().run {
                        try {
                            val response = execute()
                            if (response.isSuccessful) {
                                val body = response.body()
                                Log.d(TAG, "onResponse: ${body?.message}")
                                body?.data?.let { cacheVoiceList(it) }
                                withContext(Dispatchers.Main) {
                                    listener.onChanged(VoiceData(body?.status, body?.message, body?.data))
                                }
                            }
                        } catch (e: Exception) {
                            Log.d(TAG, "getVoiceList: ${e.message}")
                            withContext(Dispatchers.Main) {
                                listener.onChanged(VoiceData(0, e.message))
                            }
                        }
                    }
                }
            }
        }
    }

    fun transform(text: String, name: String, voice: String, volume: String, rate: String, listener: DataChangedListener<WriteData>) {
        val params = mapOf("text" to text, "name" to name, "password" to voice, "volume" to volume, "rate" to rate)
        val call = RetrofitClient.apiService.transform(NetUtils.createJsonBody(params))
        call.enqueue(object : Callback<SResponse<TransformInfo>> {
            override fun onResponse(call: Call<SResponse<TransformInfo>>, response: Response<SResponse<TransformInfo>>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    Log.d(TAG, "onResponse: ${body?.message}")
                    listener.onChanged(WriteData(body?.status, body?.message))
                }
            }

            override fun onFailure(call: Call<SResponse<TransformInfo>>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
                listener.onChanged(WriteData(0, t.message))
            }
        })
    }


    private fun cacheVoiceList(data: List<VoiceInfo>) {
        dbHelper.voiceDao().insertAll(data)
    }

}