package com.example.a_level.allalcohol

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.a_level.allalcohol.model.response.Alcohol
import com.example.a_level.allalcohol.service.AllAlcoholService
import com.example.a_level.common.AlcoholDetailActivity
import com.example.a_level.databinding.ActivityAllalcoholsearchBinding
import com.example.a_level.mypage.AddAlcoholActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllAlcoholSearchActivity : AppCompatActivity() {
    lateinit var binding: ActivityAllalcoholsearchBinding
    private lateinit var allAlcoholSearchAdapter: AllAlcoholSearchAdapter
    private lateinit var alcohols: ArrayList<Alcohol>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllalcoholsearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initActionBar()
        loadDataList()
    }

    private fun initActionBar() {
        setSupportActionBar(binding.toolbarAllalcoholsearch)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
    }

    private fun loadDataList() {
        alcohols = arrayListOf()
        AllAlcoholService.searchAlcohol(keyword = "")
            .enqueue(object : Callback<ArrayList<Alcohol>> {
                override fun onResponse(
                    call: Call<ArrayList<Alcohol>>,
                    response: Response<ArrayList<Alcohol>>
                ) {
                    if (response.code() == 200) {
                        val body = response.body()
                        if (body != null) {
                            alcohols = body
                            initRecyclerView()
                            initSearchViewListener()
                        }
                    } else {
                        Log.e("AllAlcoholSearch", "${response.code()} error")
                    }
                }

                override fun onFailure(call: Call<ArrayList<Alcohol>>, t: Throwable) {
                    Log.e("AllAlcoholSearch", t.localizedMessage)
                }
            })
    }

    private fun initRecyclerView() {
        allAlcoholSearchAdapter =
            AllAlcoholSearchAdapter(this, alcohols, SearchInterface())
        binding.recyclerviewAllalcoholsearch.apply {
            layoutManager =
                GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = allAlcoholSearchAdapter.apply {
                setOnItemClickListener(object :
                    AllAlcoholSearchAdapter.OnItemClickListener {
                    override fun onItemClick(v: View, item: Alcohol) {
                        val intent = Intent(applicationContext, AlcoholDetailActivity::class.java)
                        intent.putExtra("alcoholId", item.id)
                        startActivity(intent)
                    }
                })
            }
        }
    }

    private fun initSearchViewListener() {
        binding.searchviewAllalcoholsearch.setOnEditorActionListener { _, id, _ ->
            if (id == EditorInfo.IME_ACTION_SEARCH) {
                allAlcoholSearchAdapter.filter.filter(binding.searchviewAllalcoholsearch.text)
                true
            }
            false
        }
    }

    inner class SearchInterface {
        fun setCount(count: Int) {
            if (count == 0) {
                binding.recyclerviewAllalcoholsearch.visibility = View.GONE
                binding.linearlayoutAllalcoholsearchCount.visibility = View.GONE
                binding.textviewAllalcoholsearchNoresult.visibility = View.VISIBLE
                binding.textviewAllalcoholsearchAddalcohol.visibility = View.VISIBLE
                binding.textviewAllalcoholsearchAddalcohol.setOnClickListener {
                    startActivity(Intent(applicationContext, AddAlcoholActivity::class.java))
                }
            } else {
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