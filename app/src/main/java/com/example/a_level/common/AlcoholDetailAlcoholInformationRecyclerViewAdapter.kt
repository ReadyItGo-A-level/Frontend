package com.example.a_level.common

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a_level.databinding.ItemAlcoholdetailRecyclerviewinformationBinding

class AlcoholDetailAlcoholInformationRecyclerViewAdapter(var list: ArrayList<String>) :
    RecyclerView.Adapter<AlcoholDetailAlcoholInformationRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemAlcoholdetailRecyclerviewinformationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class Holder(private val binding: ItemAlcoholdetailRecyclerviewinformationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.textviewAlcoholdetailInformation.text = item
        }
    }
}
