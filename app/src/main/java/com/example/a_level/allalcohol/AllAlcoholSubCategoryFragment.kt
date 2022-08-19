package com.example.a_level.allalcohol

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.a_level.R
import com.example.a_level.common.AlcoholDetailActivity
import com.example.a_level.databinding.FragmentAllalcoholsubcategoryBinding


class AllAlcoholSubCategoryFragment : Fragment(R.layout.fragment_allalcoholsubcategory),
    AdapterView.OnItemSelectedListener {
    private lateinit var binding: FragmentAllalcoholsubcategoryBinding
    private lateinit var allAlcoholSubCategoryRecyclerViewData: ArrayList<AllAlcoholSubCategoryRecyclerViewData>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllalcoholsubcategoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadDataList()
        initRecyclerView()
        initSpinner()
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
        binding.recyclerviewAllalcoholsubcategory.apply {
            layoutManager =
                GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
            adapter = AllAlcoholSubCategoryRecyclerViewAdapter(
                requireContext(),
                allAlcoholSubCategoryRecyclerViewData
            ).apply {
                setOnItemClickListener(object :
                    AllAlcoholSubCategoryRecyclerViewAdapter.OnItemClickListener {
                    override fun onItemClick(v: View, item: AllAlcoholSubCategoryRecyclerViewData) {
                        startActivity(Intent(requireContext(), AlcoholDetailActivity::class.java))
                    }
                })
            }
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
                val data = spinner.getItemAtPosition(position) as AllAlcoholSubCategorySpinnerData
                list[previousPosition] =
                    AllAlcoholSubCategorySpinnerData(null, list[previousPosition].name)
                list[position] =
                    AllAlcoholSubCategorySpinnerData(R.drawable.allalcoholsubcategory_spinnerdropdownicon, data.name)
                adapter.notifyDataSetChanged()
                previousPosition = position
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

}