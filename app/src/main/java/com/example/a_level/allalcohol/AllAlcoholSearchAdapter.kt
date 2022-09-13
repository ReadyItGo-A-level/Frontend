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
import com.example.a_level.databinding.ItemAllalcoholsubcategoryRecyclerviewBinding

class AllAlcoholSearchAdapter(
    private val context: Context,
    private var list: ArrayList<AllAlcoholSubCategoryRecyclerViewData>,
    var searchInterface: AllAlcoholSearchActivity.SearchInterface
) :
    RecyclerView.Adapter<AllAlcoholSearchAdapter.Holder>(), Filterable {

    private var filteredList = ArrayList<AllAlcoholSubCategoryRecyclerViewData>()

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
    fun setData(list: ArrayList<AllAlcoholSubCategoryRecyclerViewData>) {
        this.list = list
        filteredList.addAll(list)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(v: View, item: AllAlcoholSubCategoryRecyclerViewData)
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
                val filtered = ArrayList<AllAlcoholSubCategoryRecyclerViewData>()
                for (data in list) {
                    if (data.title.contains(filterString)) {
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
                filteredList.addAll(p1.values as ArrayList<AllAlcoholSubCategoryRecyclerViewData>)
                notifyDataSetChanged()
            }

        }
    }

    inner class Holder(private val binding: ItemAllalcoholsubcategoryRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AllAlcoholSubCategoryRecyclerViewData) {
            binding.textviewAllalcoholsubcategoryTitle.text = item.title
            binding.textviewAllalcoholsubcategoryPrice.text = item.price.toString() + "원"
            binding.textviewAllalcoholsubcategoryVolume.text = "(" + item.volume.toString() + "ml)"
            binding.textviewAllalcoholsubcategoryAbv.text = item.abv.toString() + "(ºC)"
        }
    }
}