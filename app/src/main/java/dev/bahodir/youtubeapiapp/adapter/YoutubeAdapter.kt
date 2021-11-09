package dev.bahodir.youtubeapiapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.bahodir.youtubeapiapp.databinding.ItemYoutubeBinding
import dev.bahodir.youtubeapiapp.model.Item
import java.text.FieldPosition

class YoutubeAdapter(var listener: OnItemTouchClickListener) : ListAdapter<Item, YoutubeAdapter.VH>(DU()) {

    interface OnItemTouchClickListener {
        fun onYoutubeItemClick(item: Item, position: Int)
    }

    class DU : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id.videoId == newItem.id.videoId
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }

    inner class VH(var binding: ItemYoutubeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item, position: Int) {
            Picasso.get().load(item.snippet.thumbnails.high.url).into(binding.image)
            binding.title.text = item.snippet.title
            val sp = item.snippet.publishTime.split("T")
            binding.channelName.text = "${item.snippet.channelTitle} : 100K views : ${sp[0]}"

            itemView.setOnClickListener {
                listener.onYoutubeItemClick(item, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemYoutubeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position), position)
    }
}