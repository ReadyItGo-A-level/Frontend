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

//        recommendAllRecyclerViewData= arrayListOf()
//        recommendAllRecyclerViewData.add(RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","오이스터 베이", "쇼비농 블랑"))
//        recommendAllRecyclerViewData.add(RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","보야", "피노누아"))
//        recommendAllRecyclerViewData.add(RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","엠", "로제"))
//        recommendAllRecyclerViewData.add(RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","칸티", "모스카토 다스티"))
//        recommendAllRecyclerViewData.add(RecommendUserRecyclerViewData("R.drawable.all_alcohol_image","간치아", "모스카토 로제"))

        val dataList = arguments?.getParcelableArrayList<RecommendUserRecyclerViewData>("data")

        binding.recyclerviewAllRecommend.apply {
            if (dataList != null) {
                layoutManager =
                    GridLayoutManager(requireContext(), dataList.size, GridLayoutManager.VERTICAL, false)
                adapter =
                    RecommendAllRecyclerViewAdapter(
                        requireContext(),
                        dataList
                    )
            }

        }
    }

}