package com.example.a_level.feed

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a_level.databinding.ItemFeedRecyclerviewBinding
import com.example.a_level.databinding.ItemFeedRecyclerviewDescriptionBinding

class FeedRecyclerViewAdapter(
    private var list: ArrayList<FeedRecyclerViewData>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ViewType().DESCRIPTION){
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
        if (holder is Holder){
            holder.bind(list[position])
            holder.itemView.setOnClickListener {
                listener?.onItemClick(holder.itemView, list[position])
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return list[position].viewType
    }
    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: ArrayList<FeedRecyclerViewData>) {
        this.list = list
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(v: View, item: FeedRecyclerViewData)
    }

    private var listener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    inner class ViewType{
        val DESCRIPTION:Int = 0
        val DATA:Int = 1
    }

    inner class DescriptionHolder(private val binding: ItemFeedRecyclerviewDescriptionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FeedRecyclerViewData) {

        }
    }

    inner class Holder(private val binding: ItemFeedRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FeedRecyclerViewData) {
            binding.textviewFeeditemTitle.text = item.title
            binding.textviewFeeditemContent.text = item.content
            binding.textviewFeeditemLikecount.text = item.likeCount.toString()
            binding.textviewFeeditemScrapcount.text = item.scrapCount.toString()
            binding.textviewFeeditemCommentcount.text = item.commentCount.toString()
        }
    }
}