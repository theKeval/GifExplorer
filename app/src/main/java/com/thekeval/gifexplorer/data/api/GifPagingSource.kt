package com.thekeval.gifexplorer.data.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.thekeval.gifexplorer.data.models.TrendingResponseData
import kotlin.math.roundToInt


private const val GIPHY_STARTING_PAGE_INDEX = 1

class GifPagingSource(
    private val service: GifService
    ): PagingSource<Int, TrendingResponseData>() {

    override fun getRefreshKey(state: PagingState<Int, TrendingResponseData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TrendingResponseData> {
        val page = params.key ?: GIPHY_STARTING_PAGE_INDEX
        return try {
            val response = service.getTrending(params.loadSize, page)
            val gifData = response.data
            val maxPage = (response.pagination.totalCount/params.loadSize).toInt() - 1
            LoadResult.Page(
                data = gifData,
                prevKey = if (page == GIPHY_STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (page == maxPage) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

}