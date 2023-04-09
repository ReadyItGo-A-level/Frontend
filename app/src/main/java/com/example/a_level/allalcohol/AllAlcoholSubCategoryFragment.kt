package com.example.a_level.allalcohol

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.recyclerview.widget.GridLayoutManager
import com.example.a_level.R
import com.example.a_level.allalcohol.model.response.Alcohol
import com.example.a_level.common.AlcoholDetailActivity
import com.example.a_level.common.Const
import com.example.a_level.databinding.FragmentAllalcoholsubcategoryBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class AllAlcoholSubCategoryFragment : Fragment(R.layout.fragment_allalcoholsubcategory) {
    private lateinit var binding: FragmentAllalcoholsubcategoryBinding
    private lateinit var pagingAdapter: AllAlcoholSubCategoryPagingAdapter
    private lateinit var alcohols: List<Alcohol>
    private var type: Int? = 0
    private var category: String? = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllalcoholsubcategoryBinding.inflate(layoutInflater)
        arguments?.let {
            type = it.getInt("type")
            category = it.getString("category")
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        loadDataList()
        initSpinner()
    }

    private fun loadDataList() {
        alcohols = listOf()
        var typeToString = ""
        when (type) {
            Const.ALL -> typeToString = ""
            Const.BEER -> typeToString = "맥주"
            Const.LIQUOR -> typeToString = "양주"
            Const.WINE -> typeToString = "와인"
            Const.TRADITIONAL -> typeToString = "전통주"
        }

        val pager = Pager(PagingConfig(pageSize = 20)) {
            PagingSource(typeToString, category!!, binding)
        }

        lifecycleScope.launch {
            pager.flow.cachedIn(this).collectLatest {
                pagingAdapter.submitData(lifecycle, it)
            }
        }
    }

    private fun initRecyclerView() {
        binding.recyclerviewAllalcoholsubcategory.apply {
            layoutManager =
                GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
            pagingAdapter = AllAlcoholSubCategoryPagingAdapter(requireContext()).apply {
                setOnItemClickListener(object :
                    AllAlcoholSubCategoryPagingAdapter.OnItemClickListener {
                    override fun onItemClick(v: View, item: Alcohol) {
                        val intent = Intent(requireContext(), AlcoholDetailActivity::class.java)
                        intent.putExtra("alcoholId", item.id)
                        startActivity(intent)
                    }
                })
            }
            adapter = pagingAdapter
        }
    }

    private fun initSpinner() {
        val spinner: Spinner = binding.spinnerAllalcoholsubcategory
        val strings = resources.getStringArray(R.array.spinner_allalcoholsubcategory)
        val list = ArrayList<AllAlcoholSubCategorySpinnerData>()
        for (value in strings) {
            val data = AllAlcoholSubCategorySpinnerData(null, value)
            list.add(data)
        }
        var adapter = AllAlcoholSubCategorySpinnerAdapter(
            requireContext(),
            R.layout.item_allalcoholsubcategory_spinner,
            list
        )
        spinner.adapter = adapter
        var previousPosition: Int = 0
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val data =
                    spinner.getItemAtPosition(position) as AllAlcoholSubCategorySpinnerData
                list[previousPosition] =
                    AllAlcoholSubCategorySpinnerData(null, list[previousPosition].name)
                list[position] =
                    AllAlcoholSubCategorySpinnerData(
                        R.drawable.allalcoholsubcategory_icon_spinnerdropdown,
                        data.name
                    )
                adapter.notifyDataSetChanged()
                previousPosition = position
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }

    override fun onResume() {
        super.onResume()
        pagingAdapter.refresh()
    }
}