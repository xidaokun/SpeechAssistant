package com.speech.assistant.base.base

import com.speech.assistant.base.DbHelper

open class BaseCtl {

    protected val dbHelper: DbHelper by lazy { DbHelper() }

    fun onCreate() {
        // Override this method to initialize data
    }

    fun onCreateView() {
        // Override this method to initialize view
    }

    fun onResume() {
        // Override this method to refresh data
    }

    fun onDestroyView() {
        // Override this method to release view
    }

    fun onDestroy() {
        // Override this method to release data
    }

}