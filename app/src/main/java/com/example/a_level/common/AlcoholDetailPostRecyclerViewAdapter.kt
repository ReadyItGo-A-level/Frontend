package com.example.a_level.common

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.a_level.databinding.ItemAlcoholdetailRecyclerviewpostBinding
import com.example.a_level.feed.model.response.Post

class AlcoholDetailPostRecyclerViewAdapter(var list: List<Post>) :
    RecyclerView.Adapter<AlcoholDetailPostRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemAlcoholdetailRecyclerviewpostBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return Holder(binding)
    }

    private var listener: OnItemClickListener? = null

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener() {
            listener?.onItemClick(holder.itemView, list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<Post>) {
        this.list = list
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(v: View, item: Post)
    }

    fun onItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    inner class Holder(private val binding: ItemAlcoholdetailRecyclerviewpostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Post) {
            binding.textviewAlcoholdetailItemTitle.text = item.title
            binding.textviewAlcoholdetailItemContent.text = item.content
            binding.textviewAlcoholdetailItemLikecount.text = item.likeCount.toString()
            binding.textviewAlcoholdetailItemScrapcount.text = item.scrapCount.toString()
            binding.textviewAlcoholdetailItemCommentcount.text = item.commentCount.toString()
        }
    }
}
