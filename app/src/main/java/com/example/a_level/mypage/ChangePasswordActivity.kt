package com.example.a_level.mypage

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import com.example.a_level.R
import com.example.a_level.databinding.ActivityChangepasswordBinding
import java.util.regex.Pattern

class ChangePasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChangepasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangepasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        val toolbar = supportActionBar!!
        toolbar.setDisplayShowTitleEnabled(false)
        toolbar.setDisplayHomeAsUpEnabled(true)

        val newPassword = binding.edittextChangepasswordPassword1.text.toString()
        val checkPassword = binding.edittextChangepasswordPassword2.text.toString()

        val newPasswordEdittext = binding.edittextChangepasswordPassword1
        val checkPasswordEdittext = binding.edittextChangepasswordPassword2

        val passwordPattern =
            "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&.])[A-Za-z[0-9]$@$!%*#?&.]{8,10}$"

        val newPasswordText = binding.textviewChangepasswordPassword1
        val checkPasswordText = binding.textviewChangepasswordPassword2

        newPasswordText.visibility = View.INVISIBLE
        checkPasswordText.visibility = View.INVISIBLE

        if ((newPassword != "") && (checkPassword != "")) {
            if (Pattern.matches(passwordPattern, newPassword)) {
                newPasswordText.visibility = View.INVISIBLE
                if (newPassword == checkPassword) {
                    checkPasswordText.text = "비밀번호가 일치합니다"
                    checkPasswordText.setTextColor(Color.parseColor("#1BB40E"))
                    checkPasswordEdittext.background =
                        ContextCompat.getDrawable(this, R.drawable.myaccount_edittext_correct)
                } else {
                    checkPasswordText.visibility = View.VISIBLE
                    checkPasswordEdittext.background =
                        ContextCompat.getDrawable(this, R.drawable.myaccount_edittext_wrong)
                }
            } else {
                if (newPassword == checkPassword) {
                    checkPasswordText.text = "비밀번호가 일치합니다"
                    checkPasswordText.setTextColor(Color.parseColor("#1BB40E"))
                    checkPasswordEdittext.background =
                        ContextCompat.getDrawable(this, R.drawable.myaccount_edittext_correct)
                } else {
                    checkPasswordText.visibility = View.VISIBLE
                    checkPasswordEdittext.background =
                        ContextCompat.getDrawable(this, R.drawable.myaccount_edittext_wrong)
                }
            }
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