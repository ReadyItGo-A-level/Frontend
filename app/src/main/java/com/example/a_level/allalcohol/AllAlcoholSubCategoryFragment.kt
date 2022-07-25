package com.example.a_level.allalcohol

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.a_level.R
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
            )
        }
    }

    private fun initSpinner() {
        val spinner: Spinner = binding.spinnerAllalcoholsubcategory
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinner_allalcoholsubcategory,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

}