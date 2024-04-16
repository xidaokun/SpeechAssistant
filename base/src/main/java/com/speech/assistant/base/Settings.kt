package com.speech.assistant.base

class Settings {

    companion object {
        const val DB_NAME = "speech.db"
        const val DB_VERSION = 1

        const val COLUMN_ID = "id"

        const val USER_INFO_TABLE = "user_info"
        const val USER_ID = "user_id"
        const val USER_NAME = "user_name"
        const val AGE = "age"
        const val TOKEN = "access_token"
        const val TEL = "tel"
        const val EMAIL = "email"
        const val ADDRESS = "address"
        const val SIGNATURE = "signature"
        val CREATE_USER_INFO_QUERY = "CREATE TABLE IF NOT EXISTS $USER_INFO_TABLE (" +
                "$USER_ID INTEGER PRIMARY KEY," +
                "$USER_NAME TEXT," +
                "$AGE INTEGER," +
                "$TOKEN TEXT," +
                "$TEL TEXT," +
                "$EMAIL TEXT," +
                "$ADDRESS TEXT," +
                "$SIGNATURE TEXT)"



        const val VOICE_HISTORY_TABLE = "user_info"
        const val VOLUME = "volume"
        const val RATE = "rate"
        const val DURATION = "duration"
        const val MEDIA_PATH = "media_path"
        const val TEXT_PATH = "text_path"
        const val GENDER = "gender"
        const val LANGUAGE = "language"
        const val FAVOR = "favor"
        val CREATE_VOICE_HISTORY_QUERY = "CREATE TABLE IF NOT EXISTS $VOICE_HISTORY_TABLE (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$USER_NAME TEXT," +
                "$VOLUME TEXT," +
                "$RATE TEXT," +
                "$DURATION INTEGER," +
                "$MEDIA_PATH TEXT," +
                "$TEXT_PATH TEXT," +
                "$GENDER TEXT," +
                "$LANGUAGE TEXT," +
                "$FAVOR INTEGER)"

        const val PREFS_NAME = "com.speech.assistant"

    }
}