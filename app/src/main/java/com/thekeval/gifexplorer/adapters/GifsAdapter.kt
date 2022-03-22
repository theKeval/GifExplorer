package com.thekeval.gifexplorer.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.thekeval.gifexplorer.adapters.GifsAdapter.GifsViewHolder
import com.thekeval.gifexplorer.data.models.GiphyResponseData
import com.thekeval.gifexplorer.databinding.ListItemGifBinding
import com.thekeval.gifexplorer.views.GifFavoriteFABClicked
import com.thekeval.gifexplorer.views.SetHeartDrawable

class GifsAdapter(
    val callback: GifFavoriteFABClicked,
    val setHeartDrawable: SetHeartDrawable
) : PagingDataAdapter<GiphyResponseData, GifsViewHolder>(GifsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifsViewHolder {
        return GifsViewHolder(
            ListItemGifBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GifsViewHolder, position: Int) {
        val gif = getItem(position)
        if (gif != null) {
            holder.bind(gif, callback, setHeartDrawable)
        }
    }

    class GifsViewHolder(
        private val binding: ListItemGifBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: GiphyResponseData, callback: GifFavoriteFABClicked, setHeartDrawable: SetHeartDrawable) {
            binding.apply {
                gif = item
                gifFavFabClicked = callback
                setHeart = setHeartDrawable
                executePendingBindings()
            }
        }
    }

}

private class GifsDiffCallback : DiffUtil.ItemCallback<GiphyResponseData>() {
    override fun areItemsTheSame(oldItem: GiphyResponseData, newItem: GiphyResponseData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GiphyResponseData, newItem: GiphyResponseData): Boolean {
        return oldItem == newItem
    }
}