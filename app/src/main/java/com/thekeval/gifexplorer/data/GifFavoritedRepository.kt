package com.thekeval.gifexplorer.data

import android.util.Log
import androidx.lifecycle.asLiveData
import com.thekeval.gifexplorer.data.local.GifDao
import com.thekeval.gifexplorer.data.local.GifEntity
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "GifFavoritedRepository"

@Singleton
class GifFavoritedRepository @Inject constructor(
    private val gifDao: GifDao
) {

    suspend fun insertFavoriteGif(gifEntity: GifEntity) {
        gifDao.insertGif(gifEntity)
    }

    suspend fun removeFavoriteGif(gifEntity: GifEntity) {
        Log.d(TAG, "removeFavoriteGif: ${gifDao.deleteGif(gifEntity.gifId)}")
    }

    fun getFavoritedGifs() = gifDao.getFavoritedGifs()

    suspend fun getAllGifs() = gifDao.getAllGifs()
}