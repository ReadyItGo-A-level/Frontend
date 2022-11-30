package com.example.a_level.allalcohol

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a_level.databinding.ItemAllalcoholsubcategoryRecyclerviewBinding

class AllAlcoholSubCategoryRecyclerViewAdapter(
    private val context: Context,
    private var list: ArrayList<AllAlcoholSubCategoryRecyclerViewData>
) :
    RecyclerView.Adapter<AllAlcoholSubCategoryRecyclerViewAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemAllalcoholsubcategoryRecyclerviewBinding.inflate(
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
    fun setData(list: ArrayList<AllAlcoholSubCategoryRecyclerViewData>) {
        this.list = list
        notifyDataSetChanged() // 데이터의 변경을 화면에 반영합니다.
    }

    // ClickListener : activity나 fragment에서 반응할 수 있습니다.
    interface OnItemClickListener {
        fun onItemClick(v: View, item: AllAlcoholSubCategoryRecyclerViewData)
    }

    private var listener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
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