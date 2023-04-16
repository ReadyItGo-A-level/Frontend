package com.example.a_level.recommend

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.a_level.R
import com.example.a_level.common.AlcoholDetailActivity
import com.example.a_level.feed.FeedDetailActivity

class RecommendAllRecyclerViewAdapter(
    private val context: Context,
    private var datalist : ArrayList<RecommendUserRecyclerViewData>,

    ): RecyclerView.Adapter<RecommendAllRecyclerViewAdapter.ViewHolder>() {
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val image: ImageView =itemView.findViewById(R.id.imageview_userrecommend_alcohol)
        val place: TextView = itemView.findViewById(R.id.textview_userrecommend_place)
        val name: TextView = itemView.findViewById(R.id.textview_userrecommend_name)
        val box: ConstraintLayout =itemView.findViewById(R.id.constraintlayout_recommend_user_box)

        fun bind(data:RecommendUserRecyclerViewData){
//            if(data.image=="")
                image.setImageResource(R.drawable.all_alcohol_image)
//            else {
//                Glide.with(context)
//                    .load(data.image)
//                    .fitCenter()
//                    .error(R.drawable.all_alcohol_image)
//                    .apply(RequestOptions().override(500, 500))
//                    .into(image)
//            }

            place.text=data.place
            name.text= data.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendAllRecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user_recommend,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int =datalist.size

    //recyclerview가 viewholder를 가져와 데이터 연결할때 호출
    //적절한 데이터를 가져와서 그 데이터를 사용하여 뷰홀더의 레이아웃 채움
    override fun onBindViewHolder(holder: RecommendAllRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.bind(datalist[position])

        holder.box.setOnClickListener{

        }
    }
}