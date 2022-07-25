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
            if (!Patterns.EMAIL_ADDRESS.matcher(binding.edittextLoginEmail.text.toString()).matches()) {
                if (!wrongEmail.isVisible)
                    wrongEmail.visibility = View.VISIBLE
            } else
                wrongEmail.visibility = View.GONE

            if (binding.edittextLoginPassword.text.toString() == "1234") {
                wrongPassword.visibility = View.GONE
            } else {
                wrongPassword.visibility = View.VISIBLE
            }
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