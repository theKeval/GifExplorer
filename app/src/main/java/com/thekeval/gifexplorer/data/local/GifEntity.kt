package com.thekeval.gifexplorer.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.thekeval.gifexplorer.data.models.GiphyResponseData
import com.thekeval.gifexplorer.data.models.GiphyResponseDataImage
import com.thekeval.gifexplorer.data.models.ImageRendition

@Entity(tableName = "favorited_gifs")
data class GifEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "url")
    val url: String
)

fun GifEntity.toGiphyResponseData(): GiphyResponseData {
    return GiphyResponseData(
        id = id,
        title = name,
        images = GiphyResponseDataImage(
            ImageRendition(
                "200", "200", "", url
            )
        )
    )
}