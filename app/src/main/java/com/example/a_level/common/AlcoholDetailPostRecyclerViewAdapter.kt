package com.example.a_level.common

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.a_level.databinding.ItemAlcoholdetailRecyclerviewpostBinding

class AlcoholDetailPostRecyclerViewAdapter(var list: ArrayList<AlcoholDetailPostRecyclerViewData>) :
    RecyclerView.Adapter<AlcoholDetailPostRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemAlcoholdetailRecyclerviewpostBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnLongClickListener {
            listener?.onItemClick(holder.itemView, list[position])!!
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: ArrayList<AlcoholDetailPostRecyclerViewData>) {
        this.list = list
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(v: View, item: AlcoholDetailPostRecyclerViewData): Boolean?
    }

    private var listener: OnItemClickListener? = null
    fun setOnItemLongClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    inner class Holder(private val binding: ItemAlcoholdetailRecyclerviewpostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AlcoholDetailPostRecyclerViewData) {
            binding.textviewAlcoholdetailItemTitle.text = item.title
            binding.textviewAlcoholdetailItemContent.text = item.content
            binding.textviewAlcoholdetailItemLikecount.text = "짠 " + item.likeCount + "개"
            binding.textviewAlcoholdetailItemScrapcount.text = "스크랩 " + item.scrapCount + "개"
            binding.textviewAlcoholdetailItemCommentcount.text = "댓글 " + item.commentCount + "개"
        }
    }
}
