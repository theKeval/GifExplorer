package com.thekeval.gifexplorer.data

import com.thekeval.gifexplorer.data.local.GifDao
import com.thekeval.gifexplorer.data.local.GifEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GifFavoritedRepository @Inject constructor(
    private val gifDao: GifDao
) {

    suspend fun insertFavoriteGif(gifEntity: GifEntity) {
        gifDao.insertGif(gifEntity)
    }

    suspend fun removeFavoriteGif(gifEntity: GifEntity) {
        gifDao.deleteGif(gifEntity)
    }

    fun isFavorited(gifId: String) = gifDao.isFavorited(gifId)

    fun getFavoritedGifs() = gifDao.getFavoritedGifs()
}