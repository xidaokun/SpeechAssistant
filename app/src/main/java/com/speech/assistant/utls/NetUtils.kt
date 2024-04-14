package com.speech.assistant.utls

import com.google.gson.Gson
import okhttp3.RequestBody

class NetUtils {

    companion object {
        fun createJsonBody(params: Map<String, String>): RequestBody {
            return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), Gson().toJson(params))
        }
    }

}