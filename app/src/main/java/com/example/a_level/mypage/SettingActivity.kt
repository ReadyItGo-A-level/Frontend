package com.example.a_level.mypage

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.a_level.App
import com.example.a_level.databinding.ActivitySettingBinding
import com.example.a_level.login.StartActivity

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        val tb = supportActionBar!!
        tb.setDisplayShowTitleEnabled(false)
        tb.setDisplayHomeAsUpEnabled(true)

        binding.constraintlayoutSettingLogout.setOnClickListener {
            val intent= Intent(this, StartActivity::class.java)

            App.prefs.remove("email")
            App.prefs.remove("password")

            Toast.makeText(this,"로그아웃되었습니다.", Toast.LENGTH_SHORT).show()
            finishAffinity()
            startActivity(intent)
        }
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