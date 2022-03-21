package com.thekeval.gifexplorer.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.thekeval.gifexplorer.R
import com.thekeval.gifexplorer.data.local.GifEntity
import com.thekeval.gifexplorer.data.local.toGiphyResponseData
import com.thekeval.gifexplorer.databinding.ListItemGifBinding

class FavoriteGifAdapter :
    ListAdapter<GifEntity, FavoriteGifAdapter.ViewHolder>(
        FavoriteGifDiffCallback()
    ) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteGifAdapter.ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_gif,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteGifAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class ViewHolder(
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

        fun bind(gifEntity: GifEntity) {
            with(binding) {
                gif = gifEntity.toGiphyResponseData()
                executePendingBindings()
            }
        }
    }

}

private class FavoriteGifDiffCallback : DiffUtil.ItemCallback<GifEntity>() {

    override fun areItemsTheSame(
        oldItem: GifEntity,
        newItem: GifEntity
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: GifEntity,
        newItem: GifEntity
    ): Boolean {
        return oldItem == newItem
    }
}