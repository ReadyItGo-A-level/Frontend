package com.example.a_level.mypage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a_level.R

class WrittenCommentAdapter(val writtencommentList: ArrayList<WrittenCommentData>) :
    RecyclerView.Adapter<WrittenCommentAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WrittenCommentAdapter.CustomViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_writtencomment, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.img.setImageResource(writtencommentList.get(position).img)
        holder.main.text = writtencommentList.get(position).main
        holder.date.text = writtencommentList.get(position).date
    }

    override fun getItemCount(): Int {
        return writtencommentList.size
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img = itemView.findViewById<ImageView>(R.id.imageview_writtencomment_image)
        val main = itemView.findViewById<TextView>(R.id.textview_writtencomment_main)
        val date = itemView.findViewById<TextView>(R.id.textview_writtencomment_date)
    }
}