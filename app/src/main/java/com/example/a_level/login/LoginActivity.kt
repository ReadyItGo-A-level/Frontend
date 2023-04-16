package com.example.a_level.login

import android.R
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a_level.App
import com.example.a_level.common.MainActivity
import com.example.a_level.databinding.ActivityLoginBinding
import com.example.a_level.keyword.UserKeywordActivity
import com.example.a_level.mypage.MypageService
import com.example.a_level.mypage.UserInfoResponse
import com.example.a_level.recommend.RecommendFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

//import com.example.a_level.keyword.UserKeywordActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarLogin)
        val toolbar = supportActionBar!!
        toolbar.setDisplayShowTitleEnabled(false)
        toolbar.setDisplayHomeAsUpEnabled(true)

        binding.textviewLoginSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.buttonLogin.setOnClickListener {
            var wrongEmail = binding.textViewLoginWrongemail
            var wrongPassword = binding.textviewLoginWrongpassword
            if (!Patterns.EMAIL_ADDRESS.matcher(binding.edittextLoginEmail.text.toString())
                    .matches()
            ) {
                wrongEmail.visibility = View.VISIBLE
            } else {    //이메일 형식이 맞으면
                wrongEmail.visibility = View.GONE

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

                LoginService.getRetrofitLogin(LoginRequest(binding.edittextLoginEmail.text.toString(), binding.edittextLoginPassword.text.toString())).enqueue(object:
                    Callback<LoginResponse> {
                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>){
                        if(response.isSuccessful) {
                            wrongEmail.visibility = View.GONE
                            wrongPassword.visibility=View.GONE

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

                            App.prefs.setString("email",binding.edittextLoginEmail.text.toString())
                            App.prefs.setString("password",binding.edittextLoginPassword.text.toString())

                            Log.e("user_info", "${App.prefs.getString("token","")}")

                            if(App.prefs.getString("savePreference","false")=="true"){
                                val intent=Intent(this@LoginActivity, MainActivity::class.java)
                                finishAffinity()
                                startActivity(intent)
                            }
                            else {
                                val intent =
                                    Intent(this@LoginActivity, UserKeywordActivity::class.java)
                                finishAffinity()
                                startActivity(intent)
                            }
                        }else{
                            try {
                                val body = response.errorBody()!!.string()
                                if(response.body()?.status==401){
                                    wrongPassword.visibility=View.VISIBLE
                                }
                                if(response.body()?.status==404){
                                    Toast.makeText(this@LoginActivity, "탈퇴한 사용자입니다.", Toast.LENGTH_SHORT).show()
                                }
//                                    val gson= GsonBuilder().create()
//                                    val error=gson.fromJson(response.errorBody()!!.string())
                                //에러 Toast
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

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

}