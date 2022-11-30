package com.example.a_level.keyword

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.a_level.R
import com.example.a_level.databinding.ActivityUserKeywordBinding

class UserKeywordActivity : AppCompatActivity() {
    var click_wine=false;
    var click_beer=false;
    var click_liq=false;
    var click_trad=false;

    private lateinit var binding: ActivityUserKeywordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserKeywordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageviewKeywordWine.setOnClickListener {
            if(!click_wine){
                binding.imageviewKeywordWine.setBackgroundResource(R.drawable.userkeyword_round_orange);
                click_wine=true
                binding.buttonKeywordNext.setBackgroundResource(R.drawable.userkeyword_button_orange)
            }
            else{
                binding.imageviewKeywordWine.setBackgroundResource(R.drawable.userkeyword_round_grey);
                click_wine=false
                checkClicked()
            }
        }
        binding.imageviewKeywordBeer.setOnClickListener {
            if(!click_beer){
                binding.imageviewKeywordBeer.setBackgroundResource(R.drawable.userkeyword_round_orange);
                click_beer=true
                binding.buttonKeywordNext.setBackgroundResource(R.drawable.userkeyword_button_orange)
            }
            else{
                binding.imageviewKeywordBeer.setBackgroundResource(R.drawable.userkeyword_round_grey);
                click_beer=false
                checkClicked()
            }
        }
        binding.imageviewKeywordLiquor.setOnClickListener {
            if(!click_liq){
                binding.imageviewKeywordLiquor.setBackgroundResource(R.drawable.userkeyword_round_orange);
                click_liq=true
                binding.buttonKeywordNext.setBackgroundResource(R.drawable.userkeyword_button_orange)
            }
            else{
                binding.imageviewKeywordLiquor.setBackgroundResource(R.drawable.userkeyword_round_grey);
                click_liq=false
                checkClicked()
            }
        }
        binding.imageviewKeywordTraditional.setOnClickListener {
            if(!click_trad){
                binding.imageviewKeywordTraditional.setBackgroundResource(R.drawable.userkeyword_round_orange);
                click_trad=true
                binding.buttonKeywordNext.setBackgroundResource(R.drawable.userkeyword_button_orange)
            }
            else{
                binding.imageviewKeywordTraditional.setBackgroundResource(R.drawable.userkeyword_round_grey);
                click_trad=false
                checkClicked()
            }
        }

        binding.buttonKeywordNext.setOnClickListener{
            if(!click_beer && !click_liq && !click_trad && !click_wine)
                Toast.makeText(this, "적어도 하나의 술을 선택해주세요", Toast.LENGTH_SHORT).show()
            else {
                var list = arrayListOf<String>()

                if (click_beer)
                    list.add("맥주")
                if (click_wine)
                    list.add("와인")
                if (click_liq)
                    list.add("양주")
                if (click_trad)
                    list.add("전통주")
                var drink=list.joinToString(separator = ",")
//                Log.e("drink", "$drink")
                val intent = Intent(this, UserStyleActivity::class.java)
                intent.putExtra("drink", drink)
                startActivity(intent)
            }
        }
    }

    private fun checkClicked(){
        if(!click_beer && !click_liq && !click_trad && !click_wine)
            binding.buttonKeywordNext.setBackgroundResource(R.drawable.userkeyword_button_grey)
    }
}