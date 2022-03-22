package com.thekeval.gifexplorer.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.thekeval.gifexplorer.data.models.GiphyResponseData
import com.thekeval.gifexplorer.data.models.GiphyResponseDataImage
import com.thekeval.gifexplorer.data.models.ImageRendition

@Entity(tableName = "favorited_gifs")
data class GifEntity(

    @ColumnInfo(name = "gif_id")
    val gifId: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "url")
    val url: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
}

fun GifEntity.toGiphyResponseData(): GiphyResponseData {
    return GiphyResponseData(
        id = gifId,
        title = name,
        images = GiphyResponseDataImage(
            ImageRendition(
                "200", "200", "", url
            )
        )
    )
}