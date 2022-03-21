package com.thekeval.gifexplorer.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.thekeval.gifexplorer.adapters.TrendingGifsAdapter.TrendingGifsViewHolder
import com.thekeval.gifexplorer.data.models.GiphyResponseData
import com.thekeval.gifexplorer.databinding.ListItemGifBinding

class TrendingGifsAdapter : PagingDataAdapter<GiphyResponseData, TrendingGifsViewHolder>(TrendingDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingGifsViewHolder {
        return TrendingGifsViewHolder(
            ListItemGifBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TrendingGifsViewHolder, position: Int) {
        val gif = getItem(position)
        if (gif != null) {
            holder.bind(gif)
        }
    }


    class TrendingGifsViewHolder(
        private val binding: ListItemGifBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener { view ->
                binding.gif?.let { gif ->
                    val uri = Uri.parse(gif.images.fixedHeight.url)
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    view.context.startActivity(intent)
                }
            }
        }

        fun bind(item: GiphyResponseData) {
            binding.apply {
                gif = item
                executePendingBindings()
            }
        }
    }

}

private class TrendingDiffCallback : DiffUtil.ItemCallback<GiphyResponseData>() {
    override fun areItemsTheSame(oldItem: GiphyResponseData, newItem: GiphyResponseData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GiphyResponseData, newItem: GiphyResponseData): Boolean {
        return oldItem == newItem
    }
}