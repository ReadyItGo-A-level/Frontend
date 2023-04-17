package com.example.a_level.mypage

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.example.a_level.App
import com.example.a_level.databinding.ActivityMyaccountBinding
import com.example.a_level.databinding.ActivityWrittenpostBinding
import com.example.a_level.login.StartActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class MyAccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyaccountBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyaccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        val tb = supportActionBar!!
        tb.setDisplayShowTitleEnabled(false)
        tb.setDisplayHomeAsUpEnabled(true)

        val buttonChangeNickname = binding.buttonMyaccountChangenickname
        buttonChangeNickname.setOnClickListener {
            val intent = Intent(this, ChangeNicknameActivity::class.java)
            startActivity(intent)
        }

        val buttonChangePassword = binding.buttonMyaccountChangepassword
        buttonChangePassword.setOnClickListener {
            val intent = Intent(this, ChangePasswordActivity::class.java)
            startActivity(intent)
        }

        binding.constLogout.setOnClickListener{
            val intent=Intent(this,StartActivity::class.java)

            App.prefs.remove("email")
            App.prefs.remove("password")

            Toast.makeText(this,"로그아웃되었습니다.",Toast.LENGTH_SHORT).show()
            finishAffinity()
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        loadAccount()
    }

    private fun loadAccount(){
        MypageService.retrofitGetUserInfo().enqueue(object: Callback<UserInfoResponse> {
            override fun onResponse(
                call: Call<UserInfoResponse>,
                response: Response<UserInfoResponse>
            ) {
                if(response.isSuccessful) {
                    Log.d("log", response.toString())
                    Log.d("log", response.body().toString())

                    var data=response.body()?.data
                    if(data!=null) {
                        var username = data.username
                        var email = data.email
                        var pwd=data.password

                        binding.nickname2.text=username
                        binding.email2.text=email
                        binding.pw2.text=pwd
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

            override fun onFailure(call: Call<UserInfoResponse>, t: Throwable) {
                Log.d("TAG", "실패원인: {$t}")
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}