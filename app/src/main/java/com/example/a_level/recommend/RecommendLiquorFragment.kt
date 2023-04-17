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

        val dataList = arguments?.getParcelableArrayList<RecommendUserRecyclerViewData>("data")

        if (dataList != null && dataList.size!=0) {
            binding.recyclerviewRecommendLiquor.apply {
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
            binding.textviewRecommendliquorReady.visibility=View.VISIBLE
            binding.recyclerviewRecommendLiquor.visibility=View.GONE
        }
    }
}