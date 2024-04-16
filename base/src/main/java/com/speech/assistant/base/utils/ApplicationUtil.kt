package com.speech.assistant.base.utils
import android.app.Application
import android.content.Context

class ApplicationUtil {

    companion object {
        fun getContext(): Context? {
            val applicationClass = Class.forName("android.app.ActivityThread")
            val method = applicationClass.getMethod("currentApplication")
            return method.invoke(null) as Application
        }
    }

}