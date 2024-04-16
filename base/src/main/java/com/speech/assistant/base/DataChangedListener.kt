package com.speech.assistant.base

interface DataChangedListener<Data> {
    fun onBefore()
    fun onChanged(data : Data)
    fun onAfter(message: String?)
}