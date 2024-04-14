package com.speech.assistant.datas

data class SResponse<T>(val status: Int, val message: String, val data: T?)
