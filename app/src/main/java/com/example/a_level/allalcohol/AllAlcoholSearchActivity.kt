package com.example.a_level.allalcohol

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.a_level.common.AlcoholDetailActivity
import com.example.a_level.databinding.ActivityAllalcoholsearchBinding

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
        binding.searchviewAllalcoholsearch.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                allAlcoholSearchAdapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    inner class SearchInterface {
        fun setCount(count: Int) {
            binding.textviewAllalcoholsearchCount.visibility = View.VISIBLE
            binding.textviewAllalcoholsearchCount.text = "총 " + count.toString() + "개의 제품"
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