package com.example.a_level.recommend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.a_level.databinding.FragmentRecommendAllBinding

class RecommendAllFragment : Fragment() {
    private lateinit var binding: FragmentRecommendAllBinding
    private lateinit var recommendAllRecyclerViewData: ArrayList<RecommendUserRecyclerViewData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentRecommendAllBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataList = arguments?.getParcelableArrayList<RecommendUserRecyclerViewData>("data")

        if (dataList != null && dataList.size!=0) {
        binding.recyclerviewAllRecommend.apply {
                layoutManager =
                    GridLayoutManager(requireContext(), dataList.size, GridLayoutManager.VERTICAL, false)
                adapter =
                    RecommendAllRecyclerViewAdapter(
                        requireContext(),
                        dataList
                    )
            }
        }
        else{
            binding.textviewRecommendallReady.visibility=View.VISIBLE
            binding.recyclerviewAllRecommend.visibility=View.GONE
        }
    }

}