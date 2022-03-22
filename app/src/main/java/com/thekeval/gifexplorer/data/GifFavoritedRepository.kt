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
        Log.d(TAG, "removeFavoriteGif: ${gifDao.deleteGif(gifEntity.gifId)}")     // gifDao.deleteGif(gifEntity)
    }

    // fun isFavorited(gifId: String) = gifDao.isFavorited(gifId)
    fun isFavorited(gif: GifEntity) : Boolean {

        Log.d(TAG, "isFavorited: gifEntity=${gif.gifId}")
        val temp = getFavoritedGifs().asLiveData()
        temp.value?.forEach { gif -> Log.d(TAG, "isFavorited: gifId=${gif.gifId}, name=${gif.name}") }
        return false

//        val temp = getFavoritedGifs().asLiveData().value?.contains(gif)
//        Log.d(TAG, "isFavorited: $temp")
//        return temp ?: false
    }

    fun getFavoritedGifs() = gifDao.getFavoritedGifs()

    suspend fun getAllGifs() = gifDao.getAllGifs()
}