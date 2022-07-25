package com.example.a_level.keyword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.example.a_level.databinding.ActivityUserStyleBinding

class UserStyleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserStyleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserStyleBinding.inflate(layoutInflater)
        setContentView(binding.root)

      binding.pricebarStyle.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
          override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean){
              binding.textviewStyleMoney.text="$progress"
          }

          override fun onStartTrackingTouch(seekBar: SeekBar?) {
              binding.textviewStyleMoney.text="${seekBar!!.progress}"
          }

          override fun onStopTrackingTouch(seekBar: SeekBar?) {
              binding.textviewStyleMoney.text="${seekBar!!.progress}"
          }
      })
    }
}