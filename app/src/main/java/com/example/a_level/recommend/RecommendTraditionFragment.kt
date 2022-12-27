package com.example.a_level.recommend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.a_level.R
import com.example.a_level.databinding.FragmentRecommendTraditionBinding

class RecommendTraditionFragment : Fragment() {
   private lateinit var binding: FragmentRecommendTraditionBinding
   private lateinit var recommendTraditionRecyclerViewData: ArrayList<RecommendUserRecyclerViewData>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentRecommendTraditionBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recommendTraditionRecyclerViewData= arrayListOf()
        recommendTraditionRecyclerViewData.add(RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","오이스터 베이", "쇼비농 블랑"))
        recommendTraditionRecyclerViewData.add(RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","보야", "피노누아"))
        recommendTraditionRecyclerViewData.add(RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","엠", "로제"))
        recommendTraditionRecyclerViewData.add(RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","칸티", "모스카토 다스티"))
        recommendTraditionRecyclerViewData.add(RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","간치아", "모스카토 로제"))

        binding.recyclerviewRecommendTradition.apply {
            layoutManager =
                GridLayoutManager(requireContext(), 5, GridLayoutManager.VERTICAL, false)
            adapter = RecommendAllRecyclerViewAdapter(
                requireContext(),
                recommendTraditionRecyclerViewData
            )
        }
    }

}