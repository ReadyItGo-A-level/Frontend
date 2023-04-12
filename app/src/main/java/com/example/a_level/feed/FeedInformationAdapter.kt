package com.example.a_level.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a_level.databinding.ItemFeedInformationBinding

class FeedInformationAdapter(
    private var list: ArrayList<Pair<String, String>>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val binding = ItemFeedInformationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return Holder(binding)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is Holder) {
            holder.bind(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class Holder(private val binding: ItemFeedInformationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Pair<String, String>) {
            binding.textviewFeedinformationType.text = item.first
            binding.textviewFeedinformationContent.text = item.second
        }
    }
}