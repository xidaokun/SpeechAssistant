package com.speech.assistant.base.utils

import android.content.Context
import android.content.SharedPreferences
import com.speech.assistant.base.Settings

class PreferenceHelper() {

    companion object {
        private val sharedPref: SharedPreferences? = ApplicationUtil.getContext()?.getSharedPreferences(Settings.PREFS_NAME, Context.MODE_PRIVATE)

        fun save(key: String, value: Int) {
            val editor: SharedPreferences.Editor? = sharedPref?.edit()
            editor?.putInt(key, value)
            editor?.apply()
        }

        fun save(key: String, value: String) {
            val editor: SharedPreferences.Editor? = sharedPref?.edit()
            editor?.putString(key, value)
            editor?.apply()
        }

        fun save(key: String, value: Boolean) {
            val editor: SharedPreferences.Editor? = sharedPref?.edit()
            editor?.putBoolean(key, value)
            editor?.apply()
        }

        fun getInt(key: String): Int? {
            return sharedPref?.getInt(key, 0)
        }

        fun getString(key: String): String? {
            return sharedPref?.getString(key, null)
        }

        fun getBoolean(key: String): Boolean? {
            return sharedPref?.getBoolean(key, false)
        }
    }


}