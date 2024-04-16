package com.speech.assistant.utls

import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody

class NetUtils {

    companion object {
        fun createJsonBody(params: Map<String, String>): RequestBody {
            return RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), Gson().toJson(params))
        }
    }

}