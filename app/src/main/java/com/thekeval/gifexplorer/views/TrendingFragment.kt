package com.thekeval.gifexplorer.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.thekeval.gifexplorer.R
import com.thekeval.gifexplorer.adapters.TrendingGifsAdapter
import com.thekeval.gifexplorer.databinding.FragmentTrendingBinding
import com.thekeval.gifexplorer.viewmodels.TrendingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TrendingFragment : Fragment() {

    private lateinit var binding: FragmentTrendingBinding
    private val viewModel: TrendingViewModel by viewModels()
    private var fetchTrendingJob: Job? = null
    private val adapter = TrendingGifsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflating the layout through databinding
        binding = FragmentTrendingBinding.inflate(inflater, container, false)
        context ?: return binding.root

        binding.gifList.adapter = adapter
        fetchTrending()

        return binding.root
    }

    private fun fetchTrending() {
        // Make sure we cancel the previous job before creating a new one
        fetchTrendingJob?.cancel()
        fetchTrendingJob = lifecycleScope.launch {
            viewModel.trendingGifs().collectLatest {
                adapter.submitData(it)
            }
        }
    }

}