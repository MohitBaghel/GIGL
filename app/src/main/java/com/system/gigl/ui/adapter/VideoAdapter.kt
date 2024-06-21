package com.system.gigl.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.system.gigl.R
import com.system.gigl.models.Videos

class VideoAdapter : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    private var videos: List<Videos> = listOf()

    fun setVideos(videos: List<Videos>) {
        this.videos = videos
        notifyDataSetChanged()
    }

    class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val thumbnail: ImageView = itemView.findViewById(R.id.thumbnail)
        private val title: TextView = itemView.findViewById(R.id.title)

        fun bind(video: Videos) {
            title.text = video.title
            Glide.with(itemView.context)
                .load(video.thumbnailUrl)
                .error(R.drawable.download)
                .into(thumbnail)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(videos[position])
    }

    override fun getItemCount(): Int = videos.size
}
