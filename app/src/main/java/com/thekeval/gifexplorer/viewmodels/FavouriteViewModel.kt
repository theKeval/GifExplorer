package com.thekeval.gifexplorer.viewmodels

import androidx.lifecycle.*
import com.thekeval.gifexplorer.data.GifFavoritedRepository
import com.thekeval.gifexplorer.data.local.GifEntity
import com.thekeval.gifexplorer.data.models.GiphyResponseData
import com.thekeval.gifexplorer.data.models.toGifEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val gifFavoritedRepository: GifFavoritedRepository
): ViewModel() {

    val favoritedGifs: LiveData<List<GifEntity>> =
        gifFavoritedRepository.getFavoritedGifs().asLiveData()

    init {
        // fetchFavoritedGifs()
    }

//    fun fetchFavoritedGifs() {
//        viewModelScope.launch {
//            _favoritedGifs.value = gifFavoritedRepository.getAllGifs()
//        }
//    }

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

}