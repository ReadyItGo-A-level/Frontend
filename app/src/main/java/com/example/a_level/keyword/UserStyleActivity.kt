package com.example.a_level.keyword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.example.a_level.common.MainActivity
import com.example.a_level.databinding.ActivityUserStyleBinding

class UserStyleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserStyleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserStyleBinding.inflate(layoutInflater)
        setContentView(binding.root)

      binding.priceBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
          override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean){
              binding.money.text="$progress"
          }

          override fun onStartTrackingTouch(seekBar: SeekBar?) {
              binding.money.text="${seekBar!!.progress}"
          }

          override fun onStopTrackingTouch(seekBar: SeekBar?) {
              binding.money.text="${seekBar!!.progress}"
          }
      })

        binding.setStyle.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}