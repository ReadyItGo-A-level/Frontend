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
import com.example.a_level.feed.FeedDetailActivity

class RecommendPopularRecyclerViewAdapter (
    private val context: Context,
    private var datalist : ArrayList<RecommendPopularRecyclerViewData>
): RecyclerView.Adapter<RecommendPopularRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val title: TextView = itemView.findViewById(R.id.textview_popularrecommend_title)
        val content: TextView = itemView.findViewById(R.id.textview_popularrecommend_content)
        val likeCount: TextView = itemView.findViewById(R.id.textview_popularrecommend_lovenum)
        val scrapCount: TextView = itemView.findViewById(R.id.textview_popularrecommend_scrapnum)
        val commentCount: TextView = itemView.findViewById(R.id.textview_popularrecommend_commentnum)
        val image: ImageView =itemView.findViewById(R.id.imageview_popular_image)
        val box: ConstraintLayout =itemView.findViewById(R.id.constraintlayout_recommend_popular)

        fun bind(data:RecommendPopularRecyclerViewData){
            title.text = data.title
            content.text = data.content
            likeCount.text = data.likeCount.toString()
            scrapCount.text = data.scrapCount.toString()
            commentCount.text = data.commentCount.toString()

            if(data.image!=""){
                Glide.with(context)
                    .load(data.image)
                    .fitCenter()
                    .error(R.drawable.all_alcohol_image)
                    .apply(RequestOptions().override(500, 500))
                    .into(image)
            }
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

        holder.box.setOnClickListener {
            val intent = Intent(holder.itemView.context, FeedDetailActivity::class.java)
            intent.putExtra("id", datalist[position]?.id ?: 0)
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }
    }
}
