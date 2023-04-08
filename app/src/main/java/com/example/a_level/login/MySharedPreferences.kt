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
//    var userid: Long?
//        get() = prefs.getLong(user_info, -1)
//        set(value) = prefs.edit().putLong(user_info, value!!).apply()
//
//    var token: String?
//        get() = prefs.getString(user_token, "")
//        set(value) = prefs.edit().putString(user_token, value!!).apply()
//    /* get/set 함수 임의 설정. get 실행 시 저장된 값을 반환하며 default 값은 ""
//     * set(value) 실행 시 value로 값을 대체한 후 저장 */
//
//    var recommendAlcohol: String?
//        get() = prefs.getString(recommend_alcohol, "")
//        set(value) = prefs.edit().putString(recommend_alcohol, value!!).apply()
//
//    var recommendPost: String?
//        get()=prefs.getString(recommend_post, "")
//        set(value)=prefs.edit().putString(recommend_post, value!!).apply()
//
//    var recommendTopPost: String?
//        get()=prefs.getString(recommend_toppost, "")
//        set(value)=prefs.edit().putString(recommend_toppost, value!!).apply()
}