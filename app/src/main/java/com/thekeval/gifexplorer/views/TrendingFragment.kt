package com.thekeval.gifexplorer.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.thekeval.gifexplorer.R
import com.thekeval.gifexplorer.adapters.GifsAdapter
import com.thekeval.gifexplorer.data.models.GiphyResponseData
import com.thekeval.gifexplorer.data.models.toGifEntity
import com.thekeval.gifexplorer.databinding.FragmentTrendingBinding
import com.thekeval.gifexplorer.viewmodels.TrendingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val TAG = "TrendingFragment"

@AndroidEntryPoint
class TrendingFragment : Fragment() {

    private lateinit var binding: FragmentTrendingBinding
    private val viewModel: TrendingViewModel by viewModels()
    private var fetchGifsJob: Job? = null
    // private val adapter = GifsAdapter()
    private var adapter: GifsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflating the layout through databinding
        binding = FragmentTrendingBinding.inflate(inflater, container, false)
        context ?: return binding.root

        binding.etSearch.doOnTextChanged { text, start, before, count ->
            viewModel.onSearch(text.toString())
        }

        viewModel.searchText.observe(viewLifecycleOwner, Observer { query ->
            fetchGifs(query)
        })

        adapter = GifsAdapter(GifFavoriteFABClicked {
            Log.d(TAG, "onCreateView: is favorited=${viewModel.isFavorited(it.id)}")

            if(viewModel.isFavorited(it.id)) {
                Log.d(TAG, "onCreateView: removing from favorite ${it.id}")
                viewModel.removeFromFavorite(it)
                viewModel.setExecutePendingBindings(false)
            }
            else {
                Log.d(TAG, "onCreateView: adding to favorite ${it.id}")
                viewModel.addToFavorite(it)
                viewModel.setExecutePendingBindings(true)
            }

            viewModel.fetchFavoritedGifs()

            // viewModel.setExecutePendingBindings(false)

        }, SetHeartDrawable {
            if (viewModel.isFavorited(it.id)) {
                R.drawable.ic_heart_filled
            }
            else {
                R.drawable.ic_heart
            }
        }, viewModel.executePendingBindings, viewLifecycleOwner)

        binding.gifList.adapter = adapter
        fetchGifs()

        return binding.root
    }

    private fun fetchGifs(query: String = "") {
        // Make sure we cancel the previous job before creating a new one
        fetchGifsJob?.cancel()
        fetchGifsJob = lifecycleScope.launch {
            viewModel.fetchGifs(query).collectLatest {
                adapter?.submitData(it)
            }
        }
    }

}

class GifFavoriteFABClicked(val block: (GiphyResponseData) -> Unit) {
    fun onClicked(model: GiphyResponseData) = block(model)
}

class SetHeartDrawable(val block: (GiphyResponseData) -> Int) {
    fun setHeartDrawable(model: GiphyResponseData) = block(model)
}