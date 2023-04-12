package com.example.a_level.allalcohol

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.a_level.App
import com.example.a_level.R
import com.example.a_level.allalcohol.model.response.Alcohol
import com.example.a_level.common.Const
import com.example.a_level.databinding.ItemAllalcoholsubcategoryRecyclerviewBinding

class AllAlcoholSubCategoryPagingAdapter(
    val context: Context
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
        val currentItem = getItem(position)!!
        if (currentItem != null) {
            holder.bind(currentItem)
            holder.itemView.setOnClickListener {
                listener?.onItemClick(holder.itemView, currentItem)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
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
            if (item.scraped == true)
                binding.imageviewAllalcoholsubcategoryItemScrap.setImageResource(R.drawable.all_icon_scrap24_full)
            else
                binding.imageviewAllalcoholsubcategoryItemScrap.setImageResource(R.drawable.all_icon_scrap24)

            if (item.image != null && item.image != "null") {
                binding.textviewAllalcoholsubcategoryItemLiquor.visibility = View.GONE
                val glideUrl = GlideUrl(
                    Const.IMG_BASE_URL + item.image,
                    LazyHeaders.Builder()
                        .addHeader("Authorization", "Bearer " + App.prefs.getString("token", ""))
                        .build()
                )
                Glide.with(binding.root.context).load(glideUrl)
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .centerCrop()
                    .into(binding.imageviewAllalcoholsubcategoryItemLiquor)
            } else {
                Glide.with(binding.root.context)
                    .clear(binding.imageviewAllalcoholsubcategoryItemLiquor)
                binding.imageviewAllalcoholsubcategoryItemLiquor.setImageDrawable(null)
                binding.textviewAllalcoholsubcategoryItemLiquor.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Alcohol>() {
            override fun areItemsTheSame(oldItem: Alcohol, newItem: Alcohol): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Alcohol, newItem: Alcohol): Boolean {
                return oldItem == newItem
            }

        }
    }
}

