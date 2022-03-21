package com.thekeval.gifexplorer.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.thekeval.gifexplorer.data.GifTrendingRepository
import com.thekeval.gifexplorer.data.models.GiphyResponseData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TrendingViewModel @Inject constructor(
    private val repository: GifTrendingRepository
) : ViewModel() {

    private var currentGiphyResult: Flow<PagingData<GiphyResponseData>>? = null

    fun trendingGifs(): Flow<PagingData<GiphyResponseData>> {
        val newResult: Flow<PagingData<GiphyResponseData>> =
            repository.getTrendingGifStream().cachedIn(viewModelScope)
        currentGiphyResult = newResult
        return newResult
    }

}