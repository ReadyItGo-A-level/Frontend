package com.example.a_level.mypage

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import com.example.a_level.databinding.ActivityAddalcoholBinding
import com.example.a_level.databinding.ActivityMainBinding

class AddAlcoholActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddalcoholBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddalcoholBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val summit = binding.summit
        summit.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("등록 완료")
                .setMessage("제보해주셔서 감사합니다!")
                .setPositiveButton("확인",
                    DialogInterface.OnClickListener { dialog, id ->
                        finish()
                    })
            builder.show()
        }

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