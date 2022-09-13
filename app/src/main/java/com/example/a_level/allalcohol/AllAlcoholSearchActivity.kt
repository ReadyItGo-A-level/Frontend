package com.example.a_level.allalcohol

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.a_level.common.AlcoholDetailActivity
import com.example.a_level.databinding.ActivityAllalcoholsearchBinding
import com.example.a_level.mypage.AddAlcoholActivity

class AllAlcoholSearchActivity : AppCompatActivity() {
    lateinit var binding: ActivityAllalcoholsearchBinding
    private lateinit var allAlcoholSearchAdapter: AllAlcoholSearchAdapter
    private lateinit var allAlcoholSubCategoryRecyclerViewData: ArrayList<AllAlcoholSubCategoryRecyclerViewData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllalcoholsearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initActionBar()
        loadDataList()
        initRecyclerView()
        initSearchViewListener()
    }

    private fun initActionBar() {
        setSupportActionBar(binding.toolbarAllalcoholsearch)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
    }

    private fun loadDataList() {
        allAlcoholSubCategoryRecyclerViewData = arrayListOf()
        allAlcoholSubCategoryRecyclerViewData.add(
            AllAlcoholSubCategoryRecyclerViewData(
                "test이름1",
                10000,
                300,
                20
            )
        )
        allAlcoholSubCategoryRecyclerViewData.add(
            AllAlcoholSubCategoryRecyclerViewData(
                "test이름2",
                20000,
                200,
                40
            )
        )
        allAlcoholSubCategoryRecyclerViewData.add(
            AllAlcoholSubCategoryRecyclerViewData(
                "test이름3",
                30000,
                500,
                10
            )
        )
        allAlcoholSubCategoryRecyclerViewData.add(
            AllAlcoholSubCategoryRecyclerViewData(
                "test이름4",
                40000,
                400,
                30
            )
        )
    }

    private fun initRecyclerView() {
        allAlcoholSearchAdapter =
            AllAlcoholSearchAdapter(this, allAlcoholSubCategoryRecyclerViewData, SearchInterface())
        binding.recyclerviewAllalcoholsearch.apply {
            layoutManager =
                GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = allAlcoholSearchAdapter.apply {
                setOnItemClickListener(object :
                    AllAlcoholSearchAdapter.OnItemClickListener {
                    override fun onItemClick(v: View, item: AllAlcoholSubCategoryRecyclerViewData) {
                        startActivity(Intent(applicationContext, AlcoholDetailActivity::class.java))
                    }
                })
            }
        }
    }

    private fun initSearchViewListener() {
        binding.searchviewAllalcoholsearch.setOnEditorActionListener { textView, id, keyEvent ->
            if (id == EditorInfo.IME_ACTION_SEARCH){
                allAlcoholSearchAdapter.filter.filter(binding.searchviewAllalcoholsearch.text)
                true
            }
            false
        }
    }

    inner class SearchInterface {
        fun setCount(count: Int) {
            if (count == 0){
                binding.recyclerviewAllalcoholsearch.visibility = View.GONE
                binding.linearlayoutAllalcoholsearchCount.visibility = View.GONE
                binding.textviewAllalcoholsearchNoresult.visibility = View.VISIBLE
                binding.textviewAllalcoholsearchAddalcohol.visibility = View.VISIBLE
                binding.textviewAllalcoholsearchAddalcohol.setOnClickListener {
                    startActivity(Intent(applicationContext, AddAlcoholActivity::class.java))
                }
            }
            else {
                binding.textviewAllalcoholsearchNoresult.visibility = View.GONE
                binding.textviewAllalcoholsearchAddalcohol.visibility = View.GONE
                binding.linearlayoutAllalcoholsearchCount.visibility = View.VISIBLE
                binding.recyclerviewAllalcoholsearch.visibility = View.VISIBLE
                binding.textviewAllalcoholsearchCount.text = count.toString()
            }
        }
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