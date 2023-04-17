package com.example.a_level.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a_level.databinding.ItemFeeddetailRecyclerviewBinding
import com.example.a_level.feed.model.response.Comment
import com.example.a_level.feed.model.response.Comments

class FeedDetailCommentAdapter(
    private var list: ArrayList<Comments>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val binding = ItemFeeddetailRecyclerviewBinding.inflate(
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

    inner class Holder(private val binding: ItemFeeddetailRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Comments) {
            binding.textviewFeeddetailItemUsername.text = item.username
            binding.textviewFeeddetailItemContent.text = item.content
            binding.textviewFeeddetailItemDate.text = item.modifiedDate
        }
    }
}