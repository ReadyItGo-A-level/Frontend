package com.example.a_level.mypage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a_level.R

class ScrapPostAdapter(val scrappostList: ArrayList<ScrapPostData>) : RecyclerView.Adapter<ScrapPostAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ScrapPostAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_scrappost, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScrapPostAdapter.CustomViewHolder, position: Int) {
        holder.img.setImageResource(scrappostList.get(position).img)
        holder.title.text = scrappostList.get(position).title
        holder.main.text = scrappostList.get(position).main
        holder.date.text = scrappostList.get(position).date
        holder.comment.text = scrappostList.get(position).comment.toString()
    }

    override fun getItemCount(): Int {
        return scrappostList.size
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img = itemView.findViewById<ImageView>(R.id.imageview_scrappost_image)
        val title = itemView.findViewById<TextView>(R.id.textview_scrappost_title)
        val main = itemView.findViewById<TextView>(R.id.textview_scrappost_main)
        val date = itemView.findViewById<TextView>(R.id.textview_scrappost_date)
        val comment = itemView.findViewById<TextView>(R.id.textview_scrappost_commentnumber)
    }
}