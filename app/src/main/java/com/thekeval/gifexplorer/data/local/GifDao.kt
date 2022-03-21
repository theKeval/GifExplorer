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

    @Delete
    suspend fun deleteGif(gif: GifEntity)

    @Query("SELECT * FROM favorited_gifs")
    fun getFavoritedGifs(): Flow<List<GifEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorited_gifs WHERE id = :gifId LIMIT 1)")
    fun isFavorited(gifId: String): Flow<Boolean>

}