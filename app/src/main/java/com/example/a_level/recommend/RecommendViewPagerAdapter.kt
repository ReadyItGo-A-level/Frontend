package com.example.a_level.recommend

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class RecommendViewPagerAdapter(
    fragmentActivity: RecommendFragment,
    private var alcoholList: RecommendAlcoholData,
    private var typeList: ArrayList<String>
): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = typeList.size

    override fun createFragment(position: Int): Fragment {
        if(typeList[position]=="전체"){
            val recommendAllFragment=RecommendAllFragment()
            val bundle=alcoholList.all
//            recommendAllFragment.arguments=bundle
            return RecommendAllFragment()
        }
        else if(typeList[position]=="와인"){
            return RecommendWineFragment()
        }
        else if(typeList[position]=="맥주"){
            return RecommendBeerFragment()
        }
        else if(typeList[position]=="전통주"){
            return RecommendTraditionFragment()
        }
        else{
            return RecommendLiquorFragment()
        }
    }
}