package com.system.gigl.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.system.gigl.R
import com.system.gigl.models.Shorts


class ShortAdapter : RecyclerView.Adapter<ShortAdapter.ShortViewHolder>() {

    private var shorts: List<Shorts> = listOf()

    fun setShorts(shorts: List<Shorts>) {
        this.shorts = shorts
        notifyDataSetChanged()
    }

    class ShortViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val thumbnail: ImageView = itemView.findViewById(R.id.thumbnail)
        private val title: TextView = itemView.findViewById(R.id.title)

        fun bind(short: Shorts) {
            title.text = short.title
            Glide
                .with(itemView.context)
                .load(short.thumbnailUrl)
                .error(R.drawable.download)
                .into(thumbnail)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShortViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_short, parent, false)
        return ShortViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShortViewHolder, position: Int) {
        holder.bind(shorts[position])
    }

    override fun getItemCount(): Int = shorts.size
}
