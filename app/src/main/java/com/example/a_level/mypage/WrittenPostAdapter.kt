package com.example.a_level.mypage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a_level.R

class WrittenPostAdapter(val writtenpostList: ArrayList<WrittenPostData>) :
    RecyclerView.Adapter<WrittenPostAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WrittenPostAdapter.CustomViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_writtenpost, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.img.setImageResource(writtenpostList.get(position).img)
        holder.title.text = writtenpostList.get(position).title
        holder.main.text = writtenpostList.get(position).main
        holder.date.text = writtenpostList.get(position).date
        holder.comment.text = writtenpostList.get(position).comment.toString()
    }

    override fun getItemCount(): Int {
        return writtenpostList.size
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img = itemView.findViewById<ImageView>(R.id.imageview_writtenpost_image)
        val title = itemView.findViewById<TextView>(R.id.textview_writtenpost_title)
        val main = itemView.findViewById<TextView>(R.id.textview_writtenpost_main)
        val date = itemView.findViewById<TextView>(R.id.textview_writtenpost_date)
        val comment = itemView.findViewById<TextView>(R.id.textview_writtenpost_commentnumber)
    }
}