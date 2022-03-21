package com.thekeval.gifexplorer.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.thekeval.gifexplorer.data.GifFavoritedRepository
import com.thekeval.gifexplorer.data.local.GifEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject internal constructor(
    gifFavoritedRepository: GifFavoritedRepository
): ViewModel() {

    val favoritedGifs: LiveData<List<GifEntity>> =
        gifFavoritedRepository.getFavoritedGifs().asLiveData()

}