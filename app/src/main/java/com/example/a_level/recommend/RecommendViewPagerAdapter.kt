package com.example.a_level.recommend

import android.os.Bundle
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
            val bundle = Bundle()
            bundle.putParcelableArrayList("data", alcoholList.all)
            recommendAllFragment.arguments=bundle

            return recommendAllFragment
        }
        else if(typeList[position]=="와인"){
            val recommendWineFragment=RecommendWineFragment()
            val bundle = Bundle()
            bundle.putParcelableArrayList("data", alcoholList.wine)
            recommendWineFragment.arguments=bundle

            return recommendWineFragment
        }
        else if(typeList[position]=="맥주"){
            val recommendBeerFragment=RecommendBeerFragment()
            val bundle = Bundle()
            bundle.putParcelableArrayList("data", alcoholList.beer)
            recommendBeerFragment.arguments=bundle

            return recommendBeerFragment
        }
        else if(typeList[position]=="전통주"){
            val recommendTraditionFragment=RecommendTraditionFragment()
            val bundle = Bundle()
            bundle.putParcelableArrayList("data", alcoholList.tradition)
            recommendTraditionFragment.arguments=bundle

            return recommendTraditionFragment
        }
        else{
            val recommendLiquorFragment=RecommendLiquorFragment()
            val bundle = Bundle()
            bundle.putParcelableArrayList("data", alcoholList.liquor)
            recommendLiquorFragment.arguments=bundle

            return recommendLiquorFragment
        }
    }
}