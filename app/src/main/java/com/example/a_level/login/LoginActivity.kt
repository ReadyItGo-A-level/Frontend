package com.example.a_level.login

import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.a_level.api.*
import com.example.a_level.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
//            var wrongEmail = binding.textViewLoginWrongemail
//            var wrongPassword = binding.textviewLoginWrongpassword
//            if (!Patterns.EMAIL_ADDRESS.matcher(binding.edittextLoginEmail.text.toString())
//                    .matches()
//            ) {
//                if (!wrongEmail.isVisible)
//                    wrongEmail.visibility = View.VISIBLE
//            } else {    //이메일 형식이 맞으면
//                wrongEmail.visibility = View.GONE
//
//                //로그인 api
//
//            }
//
//            if (binding.edittextLoginPassword.text.toString() == "1234") {
//                wrongPassword.visibility = View.GONE
//            } else {
//                wrongPassword.visibility = View.VISIBLE
//            }

            LoginService.getRetrofitLogin().getLogin("user@email.com", "user123").enqueue(object:
                Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>){
                    Log.d("log", response.toString())
                    Log.d("log", response.body().toString())
                    //로그인 성공
                    //로그인 실패
                    //계정 없음
                }
                override fun onFailure(call: Call<LoginResponse>, error: Throwable){
                    Log.d("TAG", "실패원인: {$error}")
                }
            })

//            val intent = Intent(this, UserKeywordActivity::class.java)
//            startActivity(intent)
        }


        val password = binding.edittextLoginPassword.text.toString()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

}