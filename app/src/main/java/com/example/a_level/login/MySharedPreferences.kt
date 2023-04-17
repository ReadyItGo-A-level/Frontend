package com.example.a_level.login

import android.content.Context
import android.content.SharedPreferences
import com.example.a_level.App.Companion.prefs
import com.example.a_level.recommend.RecommendAlcohol

class MySharedPreferences(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("prefs_name", Context.MODE_PRIVATE)

    fun getString(key: String, defValue: String): String {
        return prefs.getString(key, defValue).toString()
    }

    fun setString(key: String, str: String) {
        prefs.edit().putString(key, str).apply()
    }

    fun getLong(key: String, defValue: Long): Long{
        return prefs.getLong(key, defValue)
    }

    fun setLong(key:String,value:Long){
        prefs.edit().putLong(key,value).apply()
    }

    fun remove(key: String){
        prefs.edit().remove(key).commit()
    }
}