package com.speech.assistant.base.base

import androidx.room.Room
import com.speech.assistant.base.Settings
import com.speech.assistant.base.db.AppDatabase
import com.speech.assistant.base.utils.ApplicationUtil


open class BaseCtl {

    protected val dbHelper: AppDatabase by lazy {
        ApplicationUtil.getContext()?.let {
            Room.databaseBuilder(it, AppDatabase::class.java, Settings.DB_NAME).build()
        } ?: throw IllegalStateException("Context is null")
    }

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