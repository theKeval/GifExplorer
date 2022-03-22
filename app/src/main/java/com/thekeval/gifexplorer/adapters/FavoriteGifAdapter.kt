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
import com.thekeval.gifexplorer.views.GifFavoriteFABClicked
import com.thekeval.gifexplorer.views.SetHeartDrawable

class FavoriteGifAdapter(
    val callback: GifFavoriteFABClicked,
    val setHeartDrawable: SetHeartDrawable
) : ListAdapter<GifEntity, FavoriteGifAdapter.ViewHolder>(FavoriteGifDiffCallback()) {

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
        holder.bind(getItem(position), callback, setHeartDrawable)
    }


    class ViewHolder(
        private val binding: ListItemGifBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(gifEntity: GifEntity, callback: GifFavoriteFABClicked, setHeartDrawable: SetHeartDrawable) {
            with(binding) {
                gif = gifEntity.toGiphyResponseData()
                gifFavFabClicked = callback
                setHeart = setHeartDrawable
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