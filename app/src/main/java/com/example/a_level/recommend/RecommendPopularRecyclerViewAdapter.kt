package com.example.a_level.recommend

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a_level.R

class RecommendPopularRecyclerViewAdapter (
    private val context: Context,
    private var datalist : ArrayList<RecommendPopularRecyclerViewData>
): RecyclerView.Adapter<RecommendPopularRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val title: TextView = itemView.findViewById(R.id.textview_popularrecommend_title)
        val content: TextView = itemView.findViewById(R.id.textview_popularrecommend_content)
        val lovenum: TextView = itemView.findViewById(R.id.textview_popularrecommend_lovenum)
        val scrapnum: TextView = itemView.findViewById(R.id.textview_popularrecommend_scrapnum)
        val commentnum: TextView = itemView.findViewById(R.id.textview_popularrecommend_commentnum)

        fun bind(data:RecommendPopularRecyclerViewData){
            title.text = data.title
            content.text = data.content
            lovenum.text = data.lovenum
            scrapnum.text = data.scrapnum
            commentnum.text = data.commentnum
        }
    }

    //만들어진 뷰홀더 없을때 뷰홀더(레이아웃) 생성하는 함수
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_popular_recommend,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datalist.size

    //recyclerview가 viewholder를 가져와 데이터 연결할때 호출
    //적절한 데이터를 가져와서 그 데이터를 사용하여 뷰홀더의 레이아웃 채움
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datalist[position])
    }
}
