package com.example.a_level.login

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import com.example.a_level.databinding.ActivityLoginBinding
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import java.util.regex.Pattern

//import com.example.a_level.keyword.UserKeywordActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarLogin)
        val tb = supportActionBar!!
        tb.setDisplayShowTitleEnabled(false)
        tb.setDisplayHomeAsUpEnabled(true)

        binding.textViewLoginSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.buttonLogin.setOnClickListener {
            var wrongEmail = binding.textViewLoginWrongEmail
            var wrongPwd = binding.textViewLoginWrongPassword
            if (!Patterns.EMAIL_ADDRESS.matcher(binding.editTextLoginEmail.text.toString()).matches()) {
                if (!wrongEmail.isVisible)
                    wrongEmail.visibility = View.VISIBLE
            } else
                wrongEmail.visibility = View.GONE

            if (binding.editTextLoginPassword.text.toString() == "1234") {
                wrongPwd.visibility = View.GONE
            } else {
                wrongPwd.visibility = View.VISIBLE
            }
//            val intent = Intent(this, UserKeywordActivity::class.java)
//            startActivity(intent)
        }


        val pwd = binding.editTextLoginPassword.text.toString()


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

}