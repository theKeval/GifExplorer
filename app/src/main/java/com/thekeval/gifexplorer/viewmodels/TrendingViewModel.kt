package com.thekeval.gifexplorer.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.thekeval.gifexplorer.data.GifTrendingRepository
import com.thekeval.gifexplorer.data.models.TrendingResponseData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TrendingViewModel @Inject constructor(
    private val repository: GifTrendingRepository
) : ViewModel() {

    private var currentTrendingResult: Flow<PagingData<TrendingResponseData>>? = null

    fun trendingGifs(): Flow<PagingData<TrendingResponseData>> {
        val newResult: Flow<PagingData<TrendingResponseData>> =
            repository.getTrendingGifStream().cachedIn(viewModelScope)
        currentTrendingResult = newResult
        return newResult
    }

}