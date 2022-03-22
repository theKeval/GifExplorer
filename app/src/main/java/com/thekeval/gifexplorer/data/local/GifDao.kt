package com.thekeval.gifexplorer.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GifDao {

    @Insert
    suspend fun insertGif(gif: GifEntity): Long

    // @Delete
    @Query("DELETE FROM favorited_gifs WHERE gif_id=:gifId")
    suspend fun deleteGif(gifId: String): Int

    @Query("SELECT * FROM favorited_gifs")
    fun getFavoritedGifs(): Flow<List<GifEntity>>

    /**
     * adding this to get data without Flow, using in TrendingVM
     */
    @Query("SELECT * FROM favorited_gifs")
    suspend fun getAllGifs(): List<GifEntity>

}