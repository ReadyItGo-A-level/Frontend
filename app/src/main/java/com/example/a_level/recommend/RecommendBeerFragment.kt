package com.example.a_level.recommend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.a_level.R
import com.example.a_level.databinding.FragmentRecommendBeerBinding
import com.example.a_level.databinding.FragmentRecommendBinding

class RecommendBeerFragment : Fragment() {
   private lateinit var binding: FragmentRecommendBeerBinding
    private lateinit var recommendBeerRecyclerViewData: ArrayList<RecommendUserRecyclerViewData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentRecommendBeerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recommendBeerRecyclerViewData= arrayListOf()
        recommendBeerRecyclerViewData.add(RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","오이스터 베이", "쇼비농 블랑"))
        recommendBeerRecyclerViewData.add(RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","보야", "피노누아"))
        recommendBeerRecyclerViewData.add(RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","엠", "로제"))
        recommendBeerRecyclerViewData.add(RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","칸티", "모스카토 다스티"))
        recommendBeerRecyclerViewData.add(RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","간치아", "모스카토 로제"))

        binding.recyclerviewRecommendBeer.apply {
            layoutManager =
                GridLayoutManager(requireContext(), 5, GridLayoutManager.VERTICAL, false)
            adapter = RecommendAllRecyclerViewAdapter(
                requireContext(),
                recommendBeerRecyclerViewData
            )
        }
    }

}