package com.thekeval.gifexplorer.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.thekeval.gifexplorer.data.api.GifPagingSource
import com.thekeval.gifexplorer.data.api.GifService
import com.thekeval.gifexplorer.data.models.TrendingResponseData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GifTrendingRepository @Inject constructor(private val service: GifService) {

    fun getTrendingGifStream(): Flow<PagingData<TrendingResponseData>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { GifPagingSource(service) }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 25
    }

}