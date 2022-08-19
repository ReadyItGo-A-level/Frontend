package com.example.a_level.common

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a_level.databinding.ItemAlcoholdetailRecyclerviewreviewBinding

class AlcoholDetailReviewRecyclerViewAdapter(
    private var list: ArrayList<AlcoholDetailReviewRecyclerViewData>
) :
    RecyclerView.Adapter<AlcoholDetailReviewRecyclerViewAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemAlcoholdetailRecyclerviewreviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnLongClickListener {
            listener?.onItemLongClick(holder.itemView, list[position])!!
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: ArrayList<AlcoholDetailReviewRecyclerViewData>) {
        this.list = list
        notifyDataSetChanged()
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(v: View, item: AlcoholDetailReviewRecyclerViewData): Boolean?
    }

    private var listener: OnItemLongClickListener? = null
    fun setOnItemLongClickListener(listener: OnItemLongClickListener) {
        this.listener = listener
    }

    inner class Holder(private val binding: ItemAlcoholdetailRecyclerviewreviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AlcoholDetailReviewRecyclerViewData) {
            binding.textviewAlcoholdetailItemContent.text = item.content
            binding.textviewAlcoholdetailItemUserid.text = item.userId
            binding.textviewAlcoholdetailItemDate.text = item.date
        }
    }
}