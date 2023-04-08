package com.example.a_level.feed

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.a_level.R
import com.example.a_level.databinding.ActivityWritefeedBinding

class WriteFeedActivity :AppCompatActivity() {
    lateinit var binding: ActivityWritefeedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWritefeedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()
        spinner()
    }

    private fun initActionBar() {
        setSupportActionBar(binding.toolbarWritefeed)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "글쓰기"
    }

    private fun writeInfo(){
        binding.buttonWritefeedInfo.setOnClickListener{
            binding.linearlayoutWritefeedBefore.visibility= View.GONE

        }
    }

    private fun spinner(){
        val kindList= listOf("주종*", "맥주","와인","전통주","양주")
        val kindAdapter=ArrayAdapter(this, android.R.layout.simple_list_item_1,kindList)
        binding.spinnerWritefeedKind.adapter=kindAdapter

        binding.spinnerWritefeedKind.onItemSelectedListener= object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (!binding.spinnerWritefeedKind.getItemAtPosition(position).equals("주종*")) {

                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_write, menu)       // main_menu 메뉴를 toolbar 메뉴 버튼으로 설정
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}