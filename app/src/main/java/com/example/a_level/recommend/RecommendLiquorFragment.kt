package com.example.a_level.recommend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.a_level.R
import com.example.a_level.databinding.FragmentRecommendLiquorBinding

class RecommendLiquorFragment : Fragment() {
    private lateinit var binding: FragmentRecommendLiquorBinding
    private lateinit var recommendLiquorRecyclerViewData: ArrayList<RecommendUserRecyclerViewData>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentRecommendLiquorBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recommendLiquorRecyclerViewData= arrayListOf()
        recommendLiquorRecyclerViewData.add(RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","오이스터 베이", "쇼비농 블랑"))
        recommendLiquorRecyclerViewData.add(RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","보야", "피노누아"))
        recommendLiquorRecyclerViewData.add(RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","엠", "로제"))
        recommendLiquorRecyclerViewData.add(RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","칸티", "모스카토 다스티"))
        recommendLiquorRecyclerViewData.add(RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","간치아", "모스카토 로제"))

        binding.recyclerviewRecommendLiquor.apply {
            layoutManager =
                GridLayoutManager(requireContext(), 5, GridLayoutManager.VERTICAL, false)
            adapter = RecommendAllRecyclerViewAdapter(
                requireContext(),
                recommendLiquorRecyclerViewData
            )
        }
    }

}