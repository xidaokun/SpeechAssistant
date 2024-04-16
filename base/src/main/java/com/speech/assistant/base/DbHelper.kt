package com.speech.assistant.base

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.speech.assistant.base.Settings.Companion.CREATE_USER_INFO_QUERY
import com.speech.assistant.base.Settings.Companion.CREATE_VOICE_HISTORY_QUERY
import com.speech.assistant.base.Settings.Companion.VOICE_HISTORY_TABLE
import com.speech.assistant.base.utils.ApplicationUtil

class DbHelper() : SQLiteOpenHelper(ApplicationUtil.getContext(), Settings.DB_NAME, null, Settings.DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_USER_INFO_QUERY)
        db?.execSQL(CREATE_VOICE_HISTORY_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val DROP_USER_INFO_QUERY = "DROP TABLE IF EXISTS ${Settings.USER_INFO_TABLE}"
        val DROP_VOICE_HISTORY_QUERY = "DROP TABLE IF EXISTS $VOICE_HISTORY_TABLE"
        db?.execSQL(DROP_USER_INFO_QUERY)
        db?.execSQL(DROP_VOICE_HISTORY_QUERY)
        onCreate(db)
    }

    fun insert(tableName: String, values: ContentValues): Long {
        val db = writableDatabase
        return db.insert(tableName, null, values)
    }

    fun update(tableName: String, values: ContentValues, whereClause: String, whereArgs: Array<String>): Int {
        val db = writableDatabase
        return db.update(tableName, values, whereClause, whereArgs)
    }

    fun delete(tableName: String, whereClause: String, whereArgs: Array<String>): Int {
        val db = writableDatabase
        return db.delete(tableName, whereClause, whereArgs)
    }

    fun query(tableName: String, columns: Array<String>, selection: String, selectionArgs: Array<String>, groupBy: String, having: String, orderBy: String): Cursor {
        val db = readableDatabase
        return db.query(tableName, columns, selection, selectionArgs, groupBy, having, orderBy)
    }

}
