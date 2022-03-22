package com.thekeval.gifexplorer.adapters

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.thekeval.gifexplorer.R
import com.thekeval.gifexplorer.adapters.GifsAdapter.GifsViewHolder
import com.thekeval.gifexplorer.data.local.GifEntity
import com.thekeval.gifexplorer.data.models.GiphyResponseData
import com.thekeval.gifexplorer.databinding.ListItemGifBinding
import com.thekeval.gifexplorer.views.GifFavoriteFABClicked
import com.thekeval.gifexplorer.views.SetHeartDrawable

private const val TAG = "GifsAdapter"

class GifsAdapter(val callback: GifFavoriteFABClicked,
                  val setHeartDrawable: SetHeartDrawable,
                  val executePendingBinding: LiveData<Boolean>,
                  val viewLifeCycleOwner: LifecycleOwner
) : PagingDataAdapter<GiphyResponseData, GifsViewHolder>(GifsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifsViewHolder {
        return GifsViewHolder(
            ListItemGifBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), executePendingBinding, viewLifeCycleOwner
        )
    }

    override fun onBindViewHolder(holder: GifsViewHolder, position: Int) {
        val gif = getItem(position)
        if (gif != null) {
            holder.bind(gif, callback, setHeartDrawable, executePendingBinding)
        }
    }


    class GifsViewHolder(
        private val binding: ListItemGifBinding,
        private val executePendingBinding: LiveData<Boolean>,
        private val viewLifeCycleOwner: LifecycleOwner
    ) : RecyclerView.ViewHolder(binding.root) {
        init {

//            executePendingBinding.observe(viewLifeCycleOwner, Observer {
//                if (it) {
//                    Log.d(TAG, "GifsViewHolder: executing pending binding")
//                    // binding.executePendingBindings()
//                    // binding.fabFav.setImageResource(binding.setHeart.setHeartDrawable(giphyResData))
//                    binding.fabFav.setImageResource(R.drawable.ic_heart_filled)
//                }
//                else {
//                    binding.fabFav.setImageResource(R.drawable.ic_heart)
//                }
//            })

//            binding.setClickListener { view ->
//                binding.gif?.let { gif ->
//                    val uri = Uri.parse(gif.images.fixedHeight.url)
//                    val intent = Intent(Intent.ACTION_VIEW, uri)
//                    view.context.startActivity(intent)
//                }
//            }
        }

        lateinit var giphyResData: GiphyResponseData
        fun bind(item: GiphyResponseData,
                 callback: GifFavoriteFABClicked,
                 setHeartDrawable: SetHeartDrawable,
                 executePendingBinding: LiveData<Boolean>
        ) {
            giphyResData = item

            binding.apply {
                gif = item
                gifFavFabClicked = callback
                setHeart = setHeartDrawable
                executePendingBindings()

                executePendingBinding.observe(viewLifeCycleOwner, Observer {
                    if (it) {
                        Log.d(TAG, "GifsViewHolder: executing pending binding")
                        // binding.executePendingBindings()
                        // binding.fabFav.setImageResource(binding.setHeart.setHeartDrawable(giphyResData))
                        binding.fabFav.setImageResource(R.drawable.ic_heart_filled)
                    }
                    else {
                        binding.fabFav.setImageResource(R.drawable.ic_heart)
                    }
                })

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