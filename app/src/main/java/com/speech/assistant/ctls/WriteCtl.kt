package com.speech.assistant.ctls

import android.util.Log
import com.speech.assistant.base.DataChangedListener
import com.speech.assistant.base.base.BaseCtl
import com.speech.assistant.datas.SResponse
import com.speech.assistant.datas.TransformInfo
import com.speech.assistant.datas.VoiceInfo
import com.speech.assistant.net.RetrofitClient
import com.speech.assistant.utls.NetUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WriteCtl : BaseCtl() {

    private val TAG = "WriteCtl"

    data class VoiceData(val status: Int?, val message: String?, val data: List<VoiceInfo>? = null)

    data class WriteData(val status: Int?, val message: String?)

    fun getVoiceList(listener: DataChangedListener<VoiceData>) {
        val call = RetrofitClient.apiService.voiceList()
        call.enqueue(object : Callback<SResponse<List<VoiceInfo>>> {
            override fun onResponse(call: Call<SResponse<List<VoiceInfo>>>, response: Response<SResponse<List<VoiceInfo>>>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    Log.d(TAG, "onResponse: ${body?.message}")
                    listener.onChanged(VoiceData(body?.status, body?.message))
                }
            }

            override fun onFailure(call: Call<SResponse<List<VoiceInfo>>>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
                listener.onChanged(VoiceData(0, t.message))
            }
        })
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

    }

}