package com.thekeval.gifexplorer.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.thekeval.gifexplorer.R
import com.thekeval.gifexplorer.adapters.FavoriteGifAdapter
import com.thekeval.gifexplorer.adapters.GifsAdapter
import com.thekeval.gifexplorer.adapters.TRENDING_PAGE_INDEX
import com.thekeval.gifexplorer.databinding.FragmentFavouriteBinding
import com.thekeval.gifexplorer.viewmodels.FavouriteViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "FavouriteFragment"

@AndroidEntryPoint
class FavouriteFragment : Fragment() {

    private lateinit var binding: FragmentFavouriteBinding
    private val viewModel: FavouriteViewModel by viewModels()
    private var adapter: FavoriteGifAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        
        // Inflate the layout for this fragment
        binding = FragmentFavouriteBinding.inflate(inflater, container, false)

        adapter = FavoriteGifAdapter(GifFavoriteFABClicked {
            Log.d(TAG, "onCreateView: is favorited=${viewModel.isFavorited(it.id)}")

            if(viewModel.isFavorited(it.id)) {
                Log.d(TAG, "onCreateView: removing from favorite ${it.id}")
                viewModel.removeFromFavorite(it)
            }
            else {
                Log.d(TAG, "onCreateView: adding to favorite ${it.id}")
                viewModel.addToFavorite(it)
            }

            // viewModel.fetchFavoritedGifs()
            adapter?.notifyDataSetChanged()

        }, SetHeartDrawable {
            if (viewModel.isFavorited(it.id)) {
                R.drawable.ic_heart_filled
            }
            else {
                R.drawable.ic_heart
            }
        })

        binding.favoriteGifList.adapter = adapter

        binding.addFavorite.setOnClickListener {
            navigateToTrendingPage()
        }

        viewModel.favoritedGifs.observe(viewLifecycleOwner, Observer { listGif ->
            binding.hasFavorites = !listGif.isNullOrEmpty()
            adapter?.submitList(listGif)
        })

        return binding.root
    }


    // TODO: can be converted to data binding
    private fun navigateToTrendingPage() {
        requireActivity().findViewById<ViewPager2>(R.id.view_pager).currentItem =
            TRENDING_PAGE_INDEX
    }


}