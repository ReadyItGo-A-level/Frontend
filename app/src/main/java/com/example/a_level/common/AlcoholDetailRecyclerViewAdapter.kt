package com.example.a_level.common

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a_level.databinding.ItemAlcoholdetailRecyclerviewBinding
import com.example.a_level.databinding.ItemAllalcoholsubcategoryRecyclerviewBinding

class AlcoholDetailRecyclerViewAdapter(
    private val context: Context,
    private var list: ArrayList<AlcoholDetailRecyclerViewData>
) :
    RecyclerView.Adapter<AlcoholDetailRecyclerViewAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemAlcoholdetailRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            listener?.onItemClick(holder.itemView, list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: ArrayList<AlcoholDetailRecyclerViewData>) {
        this.list = list
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(v: View, item: AlcoholDetailRecyclerViewData)
    }

    private var listener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    inner class Holder(private val binding: ItemAlcoholdetailRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AlcoholDetailRecyclerViewData) {
            binding.textviewAlcoholdetailItemContent.text = item.content
            binding.textviewAlcoholdetailItemUserid.text = item.userId
            binding.textviewAlcoholdetailItemDate.text = item.date
        }
    }
}