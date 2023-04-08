package com.example.a_level.allalcohol

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.a_level.allalcohol.model.response.Alcohol
import com.example.a_level.databinding.ItemAllalcoholsubcategoryRecyclerviewBinding

class AllAlcoholSubCategoryPagingAdapter(

) :
    PagingDataAdapter<Alcohol, AllAlcoholSubCategoryPagingAdapter.Holder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemAllalcoholsubcategoryRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
            holder.itemView.setOnClickListener {
                listener?.onItemClick(holder.itemView, currentItem)
            }
        }
    }

    // ClickListener : activity나 fragment에서 반응할 수 있습니다.
    interface OnItemClickListener {
        fun onItemClick(v: View, item: Alcohol)
    }

    private var listener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }


    inner class Holder(private val binding: ItemAllalcoholsubcategoryRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Alcohol) {
            binding.textviewAllalcoholsubcategoryTitle.text = item.name
            binding.textviewAllalcoholsubcategoryPrice.text = item.price.toString() + "원"
            binding.textviewAllalcoholsubcategoryVolume.text = "・" + item.size.toString() + "ml"
            binding.textviewAllalcoholsubcategoryAbv.text = item.volume.toString() + "%"
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Alcohol>() {
            override fun areItemsTheSame(oldItem: Alcohol, newItem: Alcohol): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Alcohol, newItem: Alcohol): Boolean {
                return oldItem == newItem
            }

        }
    }
}

