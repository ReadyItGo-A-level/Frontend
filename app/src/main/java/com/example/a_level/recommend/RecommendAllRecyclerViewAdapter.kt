package com.example.a_level.recommend

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a_level.R

class RecommendAllRecyclerViewAdapter(
    private val context: Context,
    private var datalist : ArrayList<RecommendUserRecyclerViewData>,

    ): RecyclerView.Adapter<RecommendAllRecyclerViewAdapter.ViewHolder>() {
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val image: ImageView =itemView.findViewById(R.id.imageview_userrecommend_alcohol)
        val place: TextView = itemView.findViewById(R.id.textview_userrecommend_place)
        val name: TextView = itemView.findViewById(R.id.textview_userrecommend_name)

        fun bind(data:RecommendUserRecyclerViewData){
            image.setImageResource(R.drawable.all_alcohol_image)
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
    }
}