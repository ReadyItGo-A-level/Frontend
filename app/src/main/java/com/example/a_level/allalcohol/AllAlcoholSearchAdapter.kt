package com.example.a_level.allalcohol

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.Toast
import androidx.core.view.get
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

class AllAlcoholSearchAdapter(
    private val context: Context,
    private var list: ArrayList<Alcohol>,
    var searchInterface: AllAlcoholSearchActivity.SearchInterface
) :
    RecyclerView.Adapter<AllAlcoholSearchAdapter.Holder>(), Filterable {

    private var filteredList = ArrayList<Alcohol>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemAllalcoholsubcategoryRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(filteredList[position])
        holder.itemView.setOnClickListener {
            listener?.onItemClick(holder.itemView, filteredList[position])
        }
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: ArrayList<Alcohol>) {
        this.list = list
        filteredList.addAll(list)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(v: View, item: Alcohol)
    }

    private var listener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence): FilterResults {
                val filterString = p0.toString()
                val results = FilterResults()
                val filtered = ArrayList<Alcohol>()
                for (data in list) {
                    if (data.name!!.contains(filterString)) {
                        filtered.add(data)
                    }
                }
                results.values = filtered
                results.count = filtered.size
                return results
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(p0: CharSequence, p1: FilterResults) {
                searchInterface.setCount(p1.count)
                filteredList.clear()
                filteredList.addAll(p1.values as ArrayList<Alcohol>)
                notifyDataSetChanged()
            }

        }
    }

    inner class Holder(private val binding: ItemAllalcoholsubcategoryRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Alcohol) {
            binding.textviewAllalcoholsubcategoryTitle.text = item.name
            binding.textviewAllalcoholsubcategoryPrice.text = item.price.toString() + "원"
            binding.textviewAllalcoholsubcategoryVolume.text = "(" + item.size.toString() + "ml)"
            binding.textviewAllalcoholsubcategoryAbv.text = item.volume.toString() + "(ºC)"
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
}