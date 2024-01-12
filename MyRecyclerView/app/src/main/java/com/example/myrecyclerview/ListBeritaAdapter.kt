package com.example.myrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListBeritaAdapter(private val listberita: List<Berita>) : RecyclerView.Adapter<ListBeritaAdapter.ListViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_berita, parent, false)
        return ListViewHolder(view)
    }
    var clickListener : RecyclerViewClickListener? = null
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo) = listBerita[position]
        Glide.with(holder.itemView.context)
            .load(photo)
            .into(holder.imgPhoto)

        holder.tvName.text = name
        holder.tvDescription.text = description
        holder.itemView.setOnClickListener {
            clickListener?.onItemClicked(it, listBerita[position])
        }
    }

    override fun getItemCount(): Int = listBerita.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }


}