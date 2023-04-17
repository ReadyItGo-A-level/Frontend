package com.example.a_level.feed

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.a_level.R
import com.example.a_level.databinding.ItemFeedRecyclerviewBinding
import com.example.a_level.databinding.ItemFeedRecyclerviewDescriptionBinding
import com.example.a_level.feed.model.response.FeedList

class FeedRecyclerViewAdapter(
    private val context: Context,
    private var list: ArrayList<FeedList>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ViewType().DESCRIPTION) {
            val binding = ItemFeedRecyclerviewDescriptionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            DescriptionHolder(binding)
        } else {
            val binding = ItemFeedRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            Holder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is Holder) {
            holder.bind(list[position])
            holder.itemView.setOnClickListener {
//                listener?.onItemClick(holder.itemView, list[position])
                val intent = Intent(holder.itemView.context, FeedDetailActivity::class.java)
                intent.putExtra("id", list[position]?.id ?: 0)
                ContextCompat.startActivity(holder.itemView.context, intent, null)
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return list[position]?.let { 1 } ?: 0
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: ArrayList<FeedList>) {
        this.list = list
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(v: View, item: FeedList)
    }

    private var listener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    inner class ViewType {
        val DESCRIPTION: Int = 0
        val DATA: Int = 1
    }

    inner class DescriptionHolder(private val binding: ItemFeedRecyclerviewDescriptionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FeedList?) {

        }
    }

    inner class Holder(private val binding: ItemFeedRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FeedList) {
            if (item != null) {
                binding.textviewFeeditemTitle.text = item.title
                binding.textviewFeeditemContent.text = item.content
                binding.textviewFeeditemLikecount.text = item.likeCount.toString()
                binding.textviewFeeditemScrapcount.text = item.scrapCount.toString()
                binding.textviewFeeditemCommentcount.text = item.commentCount.toString()

                if(item.image!=""){
                    binding.imageviewFeeditem.visibility=View.VISIBLE
                    Glide.with(context)
                        .load(item.image)
                        .fitCenter()
                        .error(R.drawable.common_alcohol_test)
                        .apply(RequestOptions().override(500, 500))
                        .into(binding.imageviewFeeditem)
                }
                else{
                    binding.imageviewFeeditem.visibility=View.GONE
                }

                var sugarList=arrayListOf("1(씁쓸한)","2(중간)","3(중간)","4(매우 달달한)","5(매우 달달한)",)
                var volumeList=arrayListOf("1(낮음)","2(중간)","3(중간)","4(높음)","5(높음)",)

                val feedInformationData = ArrayList<Pair<String, String>>()
                item.alcoholType?.let { feedInformationData.add(Pair("주종", item.alcoholType)) }
                item.alcoholName?.let { feedInformationData.add(Pair("이름", item.alcoholName)) }
                if(item.volume!=0.toLong()) { feedInformationData.add(Pair("도수", volumeList[item.volume.toInt()-1])) }
                if(item.sugar!=0.toLong()) { feedInformationData.add(Pair("당도", sugarList[item.sugar.toInt()-1])) }
                if(item.price!="") { feedInformationData.add(Pair("예상가격",(item.price)))}
                if(item.flavor!=""){ feedInformationData.add(Pair("맛", item.flavor)) }

                binding.recyclerviewFeeditemInformation.adapter =
                    FeedInformationAdapter(feedInformationData)
            }
        }
    }
}