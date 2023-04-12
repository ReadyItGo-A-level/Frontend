package com.example.a_level.feed

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a_level.databinding.ItemFeedRecyclerviewBinding
import com.example.a_level.databinding.ItemFeedRecyclerviewDescriptionBinding
import com.example.a_level.feed.model.response.Post

class FeedRecyclerViewAdapter(
    private var list: ArrayList<Post?>
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
                listener?.onItemClick(holder.itemView, list[position])
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
    fun setData(list: ArrayList<Post?>) {
        this.list = list
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(v: View, item: Post?)
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
        fun bind(item: Post?) {

        }
    }

    inner class Holder(private val binding: ItemFeedRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Post?) {
            if (item != null) {
                binding.textviewFeeditemTitle.text = item.title
                binding.textviewFeeditemContent.text = item.content
                binding.textviewFeeditemLikecount.text = item.likeCount.toString()
                binding.textviewFeeditemScrapcount.text = item.scrapCount.toString()
                binding.textviewFeeditemCommentcount.text = item.commentCount.toString()

                val feedInformationData = ArrayList<Pair<String, String>>()
                item.alcoholType?.let { feedInformationData.add(Pair("주종", item.alcoholType)) }
                item.alcoholName?.let { feedInformationData.add(Pair("이름", item.alcoholName)) }
                item.volume?.let { feedInformationData.add(Pair("도수", item.volume)) }
                item.sugar?.let { feedInformationData.add(Pair("당도", item.sugar.toString())) }
                item.price?.let { feedInformationData.add(Pair("예상가격", item.price.toString())) }
                item.flavor?.let { feedInformationData.add(Pair("맛", item.flavor)) }

                binding.recyclerviewFeeditemInformation.adapter =
                    FeedInformationAdapter(feedInformationData)
            }
        }
    }
}