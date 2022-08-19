package com.example.a_level.mypage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a_level.R

class ScrapAlcoholAdapter(val scrapalcoholList: ArrayList<ScrapAlcoholData>) :
    RecyclerView.Adapter<ScrapAlcoholAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ScrapAlcoholAdapter.CustomViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_scrapalcohol, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.img.setImageResource(scrapalcoholList.get(position).img)
        holder.name.text = scrapalcoholList.get(position).name
        holder.price.text = scrapalcoholList.get(position).price
        holder.abv.text = scrapalcoholList.get(position).abv
    }

    override fun getItemCount(): Int {
        return scrapalcoholList.size
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img = itemView.findViewById<ImageView>(R.id.imageview_scrapalcohol_alcohol)
        val name = itemView.findViewById<TextView>(R.id.textview_scrapalcohol_name)
        val price = itemView.findViewById<TextView>(R.id.textview_scrapalcohol_price)
        val abv = itemView.findViewById<TextView>(R.id.textview_scrapalcohol_abv)
    }
}