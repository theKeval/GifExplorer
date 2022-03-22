package com.thekeval.gifexplorer.viewmodels

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.flatMap
import androidx.paging.map
import com.thekeval.gifexplorer.data.GifFavoritedRepository
import com.thekeval.gifexplorer.data.GifTrendingRepository
import com.thekeval.gifexplorer.data.local.GifEntity
import com.thekeval.gifexplorer.data.models.GiphyResponseData
import com.thekeval.gifexplorer.data.models.toGifEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "TrendingViewModel"

@HiltViewModel
class TrendingViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: GifTrendingRepository,
    private val gifFavoritedRepository: GifFavoritedRepository
) : ViewModel() {

    private var _searchText = MutableLiveData<String>()
    val searchText: LiveData<String>
        get() = _searchText

    private var _favoritedGifs = MutableLiveData<List<GifEntity>>()
    val favoritedGifs: LiveData<List<GifEntity>>
        get() = _favoritedGifs

    init {
        fetchFavoritedGifs()
    }

    fun onSearch(query: String) {
        _searchText.value = query
    }

    private var currentGiphyResult: Flow<PagingData<GiphyResponseData>>? = null

    fun fetchGifs(query: String = ""): Flow<PagingData<GiphyResponseData>> {
        val newResult: Flow<PagingData<GiphyResponseData>> =
            repository.getGifStream(query).cachedIn(viewModelScope)
        currentGiphyResult = newResult
        return newResult
    }

    fun fetchFavoritedGifs() {
        viewModelScope.launch {
            _favoritedGifs.value = gifFavoritedRepository.getAllGifs()
        }
    }

    fun addToFavorite(giphyResponseData: GiphyResponseData) {
        viewModelScope.launch {
            gifFavoritedRepository.insertFavoriteGif(
                giphyResponseData.toGifEntity()
            )
        }
    }

    fun removeFromFavorite(giphyResponseData: GiphyResponseData) {
        viewModelScope.launch {
            gifFavoritedRepository.removeFavoriteGif(
                giphyResponseData.toGifEntity()
            )
        }
    }

    fun isFavorited(gifId: String) : Boolean {
        favoritedGifs.value?.forEach {
            if (it.gifId == gifId)
                return true
        }

        return false
    }

//    fun isFavorited(gif: GifEntity): Boolean {
//
//        viewModelScope.launch {
//            val abc = gifFavoritedRepository.getAllGifs().forEach {
//                if (it.gifId == gif.gifId) retu
//            }
//        }
//
//
////        val liveBool = gifFavoritedRepository.isFavorited(gif)
////        Log.d(TAG, "isFavorited: ${liveBool}")
////        return liveBool     // .value ?: false
//    }

}