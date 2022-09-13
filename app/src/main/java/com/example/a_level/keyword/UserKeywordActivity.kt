package com.example.a_level.keyword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
                binding.imageviewKeywordWine.setImageResource(R.drawable.userkeyword_wine_clicked);
                click_wine=true;
            }
            else{
                binding.imageviewKeywordWine.setImageResource(R.drawable.userkeyword_wine);
                click_wine=false;
            }
        }
        binding.imageviewKeywordBeer.setOnClickListener {
            if(!click_beer){
                binding.imageviewKeywordBeer.setImageResource(R.drawable.userkeyword_beer_cilcked);
                click_beer=true;
            }
            else{
                binding.imageviewKeywordBeer.setImageResource(R.drawable.userkeyword_beer);
                click_beer=false;
            }
        }
        binding.imageviewKeywordLiquor.setOnClickListener {
            if(!click_liq){
                binding.imageviewKeywordLiquor.setImageResource(R.drawable.userkeyword_liq_clicked);
                click_liq=true;
            }
            else{
                binding.imageviewKeywordLiquor.setImageResource(R.drawable.userkeyword_liquor);
                click_liq=false;
            }
        }
        binding.imageviewKeywordTraditional.setOnClickListener {
            if(!click_trad){
                binding.imageviewKeywordTraditional.setImageResource(R.drawable.userkeyword_tradition_clicked);
                click_trad=true;
            }
            else{
                binding.imageviewKeywordTraditional.setImageResource(R.drawable.userkeyword_tradition);
                click_trad=false;
            }
        }

        binding.buttonKeywordNext.setOnClickListener{
            val intent = Intent(this, UserStyleActivity::class.java)
            startActivity(intent)
        }
    }
}