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

        val dataList = arguments?.getParcelableArrayList<RecommendUserRecyclerViewData>("data")

        if (dataList != null && dataList.size!=0) {
        binding.recyclerviewRecommendBeer.apply {
                layoutManager =
                    GridLayoutManager(requireContext(), 5, GridLayoutManager.VERTICAL, false)
                adapter =
                    RecommendAllRecyclerViewAdapter(
                        requireContext(),
                        dataList
                    )
            }
        }
        else{
            binding.textviewRecommendbeerReady.visibility=View.VISIBLE
            binding.recyclerviewRecommendBeer.visibility=View.GONE
        }
    }

}