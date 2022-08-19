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

        val summitDialog = binding.buttonAddalcoholSummit
        summitDialog.setOnClickListener {
            val dialog = AddAlcoholDialog(this@AddAlcoholActivity)
            dialog.listener = object: AddAlcoholDialog.SummitDialogClickedListener {
                override fun onSummitClicked() {
                    finish()
                }
            }
            dialog.start()
        }

        setSupportActionBar(binding.toolbar)
        val toolbar = supportActionBar!!
        toolbar.setDisplayShowTitleEnabled(false)
        toolbar.setDisplayHomeAsUpEnabled(true)
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