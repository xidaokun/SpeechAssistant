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
import java.io.File

class WriteCtl : BaseCtl() {

    private val TAG = "WriteCtl"

    data class VoiceData(val status: Int?, val message: String?, val data: List<VoiceInfo>? = null)

    data class WriteData(val status: Int?, val message: String?)

    @OptIn(DelicateCoroutinesApi::class)
    fun getVoiceList(listener: DataChangedListener<VoiceData>) {
        GlobalScope.launch {
            dbHelper.voiceDao().getAll().let {
                if (it.isNotEmpty()) {
                    Log.d(TAG, "cache voices size= ${it.size}")
                    listener.onChanged(VoiceData(1, "success", it))
                } else {
                    RetrofitClient.apiService.voiceList().run {
                        try {
                            val response = execute()
                            if (response.isSuccessful) {
                                val body = response.body()
                                Log.d(TAG, "net voices size= ${it.size}")
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

    @OptIn(DelicateCoroutinesApi::class)
    fun transform(text: String, name: String, voice: String, volume: String, rate: String, listener: DataChangedListener<WriteData>) {
        listener.onBefore()
        GlobalScope.launch {
            val params = mapOf("text" to text, "name" to name, "password" to voice, "volume" to volume, "rate" to rate)
            RetrofitClient.apiService.transform(NetUtils.createJsonBody(params)).run {
                try {
                    val response = execute()
                    if (response.isSuccessful) {
                        val body = response.body()
                        withContext(Dispatchers.Main) {
                            listener.onChanged(WriteData(body?.status, body?.message))
                            listener.onAfter(body?.message)
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            listener.onChanged(WriteData(0, response.message()))
                            listener.onAfter(response.message())
                        }
                    }
                } catch (e : Exception) {
                    withContext(Dispatchers.Main) {
                        listener.onChanged(WriteData(0, e.message))
                        listener.onAfter(e.message)
                    }
                }
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun downloadFile(outputPath: File, name: String, listener: DataChangedListener<WriteData>) {
        listener.onBefore()
        GlobalScope.launch {
            val params = mapOf( "name" to name)
            RetrofitClient.apiService.downloadFile(NetUtils.createJsonBody(params)).run {
                 try {
                    val response = execute()
                    if (response.isSuccessful) {
                        val body = response.body()
                        body?.let {
                            it.byteStream().use { input ->
                                outputPath.outputStream().use { output ->
                                    input.copyTo(output)
                                }
                            }
//                            withContext(Dispatchers.IO) {
//                            }
                        }
                        withContext(Dispatchers.Main) {
                            listener.onChanged(WriteData(1, "success"))
                            listener.onAfter("success")
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            listener.onChanged(WriteData(0, response.message()))
                            listener.onAfter(response.message())
                        }
                    }
                } catch (e : Exception) {
                    withContext(Dispatchers.Main) {
                        listener.onChanged(WriteData(0, e.message))
                        listener.onAfter(e.message)
                    }
                }
            }
        }
    }

    private fun cacheVoiceList(data: List<VoiceInfo>) {
        dbHelper.voiceDao().insertAll(data)
    }

}