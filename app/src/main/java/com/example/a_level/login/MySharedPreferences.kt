package com.example.a_level.login

import android.content.Context
import android.content.SharedPreferences
import com.example.a_level.recommend.RecommendAlcohol

class MySharedPreferences(context: Context) {
    val PREFS_FILENAME = "prefs"
    val user_info = "user_id"
    val recommend_alcohol="recommend_alcohol"
    val recommend_post="recommend_post"
    val recommend_toppost="recommend_toppost"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)
    /* 파일 이름과 EditText를 저장할 Key 값을 만들고 prefs 인스턴스 초기화 */

    var userid: Long?
        get() = prefs.getLong(user_info, -1)
        set(value) = prefs.edit().putLong(user_info, value!!).apply()
    /* get/set 함수 임의 설정. get 실행 시 저장된 값을 반환하며 default 값은 ""
     * set(value) 실행 시 value로 값을 대체한 후 저장 */

    var recommendAlcohol: String?
        get() = prefs.getString(recommend_alcohol, "")
        set(value) = prefs.edit().putString(recommend_alcohol, value!!).apply()

    var recommendPost: String?
        get()=prefs.getString(recommend_post, "")
        set(value)=prefs.edit().putString(recommend_post, value!!).apply()

    var recommendTopPost: String?
        get()=prefs.getString(recommend_toppost, "")
        set(value)=prefs.edit().putString(recommend_toppost, value!!).apply()
}