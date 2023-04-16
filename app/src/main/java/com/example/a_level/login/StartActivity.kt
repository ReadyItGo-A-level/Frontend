package com.example.a_level.login

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.a_level.App
import com.example.a_level.common.MainActivity
import com.example.a_level.databinding.ActivityStartBinding
import com.example.a_level.keyword.UserKeywordActivity
import com.example.a_level.mypage.MypageService
import com.example.a_level.mypage.UserInfoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onResume() {
        super.onResume()

        var userEmail=App.prefs.getString("email","")
        var userPassword=App.prefs.getString("password","")
        if(userEmail!=null && userPassword!=null){
            MypageService.retrofitGetUserInfo().enqueue(object:Callback<UserInfoResponse>{
                override fun onResponse(
                    call: Call<UserInfoResponse>,
                    response: Response<UserInfoResponse>
                ) {
                    if(response.isSuccessful) {
                        Log.d("log", response.toString())
                        Log.d("log", response.body().toString())

                        var username=response.body()?.data?.username
                        App.prefs.setString("username",username!!)
                    }else{
                        try {
                            val body = response.errorBody()!!.string()

                            Log.e(ContentValues.TAG, "body : $body")
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }

                override fun onFailure(call: Call<UserInfoResponse>, t: Throwable) {
                    Log.d("TAG", "실패원인: {$t}")
                }
            })

            LoginService.getRetrofitLogin(LoginRequest(userEmail, userPassword)).enqueue(object:
                Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>){
                    if(response.isSuccessful) {
                        Log.d("log", response.toString())
                        Log.d("log", response.body().toString())

                        response.body()?.data?.token?.let { it1 ->
                            App.prefs.setString("token",
                                it1
                            )
                        }
                        response.body()?.data?.id?.let{it1->
                            App.prefs.setString("userId", it1.toString())
                        }

                        Log.e("user_info", "${App.prefs.getString("token","")}")

                        Toast.makeText(this@StartActivity,"자동 로그인되었습니다.",Toast.LENGTH_LONG).show()

                        if(App.prefs.getString("savePreference","false")=="true"){
                            val intent=Intent(this@StartActivity, MainActivity::class.java)
                            finishAffinity()
                            startActivity(intent)
                        }
                        else {
                            val intent =
                                Intent(this@StartActivity, UserKeywordActivity::class.java)
                            finishAffinity()
                            startActivity(intent)
                        }
                    }else{
                        try {
                            val body = response.errorBody()!!.string()

                            Log.e(ContentValues.TAG, "body : $body")
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }

                }
                override fun onFailure(call: Call<LoginResponse>, error: Throwable){
                    Log.d("TAG", "실패원인: {$error}")
                }
            })
        }

        var content = SpannableString(binding.textviewStartLogin.text.toString())
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        binding.textviewStartLogin.text = content

        binding.buttonStartKakaoLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.buttonStartSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.textviewStartLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}