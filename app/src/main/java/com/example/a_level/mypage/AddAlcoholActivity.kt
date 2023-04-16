package com.example.a_level.mypage

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.a_level.R
import com.example.a_level.databinding.ActivityAddalcoholBinding

class AddAlcoholActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddalcoholBinding
    private var title=""
    private var reason=""
    var click_wine=false;
    var click_beer=false;
    var click_liq=false;
    var click_trad=false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddalcoholBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        val tb = supportActionBar!!
        tb.setDisplayShowTitleEnabled(false)
        tb.setDisplayHomeAsUpEnabled(true)

        clickAlcoholType()
        watchData()

        binding.linearlayoutAddalcoholSubmit.setOnClickListener{
            if((!click_beer && !click_liq && !click_trad && !click_wine) || title=="" || reason==""){
                Toast.makeText(this, "필수 정보들을 채워주세요.", Toast.LENGTH_SHORT).show()
            }else{
                val builder = AlertDialog.Builder(this)
                builder.setTitle("등록 완료")
                    .setMessage("제보해주셔서 감사합니다!")
                    .setPositiveButton("확인",
                        DialogInterface.OnClickListener { dialog, id ->
                            finish()
                        })
                builder.show()
            }
        }
    }

    private fun clickAlcoholType(){
        binding.imageviewKeywordWine.setOnClickListener {
            if(!click_wine){
                binding.imageviewKeywordWine.setBackgroundResource(R.drawable.userkeyword_round_orange);
                binding.textviewKeywordWine.setTextColor(ContextCompat.getColor(this, R.color.black))
                click_wine=true

                binding.imageviewKeywordBeer.setBackgroundResource(R.drawable.userkeyword_round_grey);
                binding.textviewKeywordBeer.setTextColor(ContextCompat.getColor(this, R.color.gray4))
                binding.imageviewKeywordLiquor.setBackgroundResource(R.drawable.userkeyword_round_grey);
                binding.textviewKeywordLiquor.setTextColor(ContextCompat.getColor(this, R.color.gray4))
                binding.imageviewKeywordTradition.setBackgroundResource(R.drawable.userkeyword_round_grey);
                binding.textviewKeywordTradition.setTextColor(ContextCompat.getColor(this, R.color.gray4))

                click_beer=false
                click_liq=false
                click_trad=false

                checkClicked()
            }
        }
        binding.imageviewKeywordBeer.setOnClickListener {
            if(!click_beer){
                binding.imageviewKeywordBeer.setBackgroundResource(R.drawable.userkeyword_round_orange);
                binding.textviewKeywordBeer.setTextColor(ContextCompat.getColor(this, R.color.black))
                click_beer=true

                binding.imageviewKeywordWine.setBackgroundResource(R.drawable.userkeyword_round_grey);
                binding.textviewKeywordWine.setTextColor(ContextCompat.getColor(this, R.color.gray4))
                binding.imageviewKeywordLiquor.setBackgroundResource(R.drawable.userkeyword_round_grey);
                binding.textviewKeywordLiquor.setTextColor(ContextCompat.getColor(this, R.color.gray4))
                binding.imageviewKeywordTradition.setBackgroundResource(R.drawable.userkeyword_round_grey);
                binding.textviewKeywordTradition.setTextColor(ContextCompat.getColor(this, R.color.gray4))

                click_wine=false
                click_liq=false
                click_trad=false

                checkClicked()

            }
        }
        binding.imageviewKeywordLiquor.setOnClickListener {
            if(!click_liq){
                binding.imageviewKeywordLiquor.setBackgroundResource(R.drawable.userkeyword_round_orange);
                binding.textviewKeywordLiquor.setTextColor(ContextCompat.getColor(this, R.color.black))
                click_liq=true

                binding.imageviewKeywordBeer.setBackgroundResource(R.drawable.userkeyword_round_grey);
                binding.textviewKeywordBeer.setTextColor(ContextCompat.getColor(this, R.color.gray4))
                binding.imageviewKeywordWine.setBackgroundResource(R.drawable.userkeyword_round_grey);
                binding.textviewKeywordWine.setTextColor(ContextCompat.getColor(this, R.color.gray4))
                binding.imageviewKeywordTradition.setBackgroundResource(R.drawable.userkeyword_round_grey);
                binding.textviewKeywordTradition.setTextColor(ContextCompat.getColor(this, R.color.gray4))

                click_beer=false
                click_wine=false
                click_trad=false

                checkClicked()

            }
        }
        binding.imageviewKeywordTradition.setOnClickListener {
            if(!click_trad){
                binding.imageviewKeywordTradition.setBackgroundResource(R.drawable.userkeyword_round_orange);
                binding.textviewKeywordTradition.setTextColor(ContextCompat.getColor(this, R.color.black))
                click_trad=true

                binding.imageviewKeywordBeer.setBackgroundResource(R.drawable.userkeyword_round_grey);
                binding.textviewKeywordBeer.setTextColor(ContextCompat.getColor(this, R.color.gray4))
                binding.imageviewKeywordLiquor.setBackgroundResource(R.drawable.userkeyword_round_grey);
                binding.textviewKeywordLiquor.setTextColor(ContextCompat.getColor(this, R.color.gray4))
                binding.imageviewKeywordWine.setBackgroundResource(R.drawable.userkeyword_round_grey);
                binding.textviewKeywordWine.setTextColor(ContextCompat.getColor(this, R.color.gray4))

                click_beer=false
                click_liq=false
                click_wine=false

                checkClicked()
            }
        }
    }

    private fun watchData(){
        binding.edittextAddalcoholName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                title = s.toString()
                checkClicked()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
        binding.edittextAddalcoholReason.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                reason = s.toString()
                checkClicked()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun checkClicked(){
        if((!click_beer && !click_liq && !click_trad && !click_wine) || title=="" || reason=="")
            binding.linearlayoutAddalcoholSubmit.setBackgroundResource(R.drawable.userkeyword_button_grey)
        else
            binding.linearlayoutAddalcoholSubmit.setBackgroundResource(R.drawable.userkeyword_button_orange)

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