package com.example.a_level.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.a_level.databinding.ActivityChangepwBinding
import com.example.a_level.databinding.ActivityScrapBinding

class ChangePwActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChangepwBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangepwBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        val tb = supportActionBar!!
        tb.setDisplayShowTitleEnabled(false)
        tb.setDisplayHomeAsUpEnabled(true)
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