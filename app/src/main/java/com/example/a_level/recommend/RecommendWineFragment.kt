package com.example.a_level.recommend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.a_level.R
import com.example.a_level.databinding.FragmentRecommendWineBinding


class RecommendWineFragment : Fragment() {
    private lateinit var binding: FragmentRecommendWineBinding
    private lateinit var recommendWineRecyclerViewData: ArrayList<RecommendUserRecyclerViewData>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentRecommendWineBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataList = arguments?.getParcelableArrayList<RecommendUserRecyclerViewData>("data")

        binding.recyclerviewRecommendWine.apply {
            if (dataList != null) {
            layoutManager =
                GridLayoutManager(requireContext(), 5, GridLayoutManager.VERTICAL, false)
            adapter =
                RecommendAllRecyclerViewAdapter(
                    requireContext(),
                    dataList
                )
            }
        }
    }
}