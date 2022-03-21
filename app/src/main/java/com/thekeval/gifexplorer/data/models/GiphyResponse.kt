package com.thekeval.gifexplorer.data.models

import com.google.gson.annotations.SerializedName

data class GiphyResponse(
    @field:SerializedName("data") val data: List<GiphyResponseData>,
    @field:SerializedName("pagination") val pagination: GiphyResponsePagination,
    @field:SerializedName("meta") val meta: GiphyResponseMeta
)

data class GiphyResponseData(
    @field:SerializedName("id") val id: String,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("images") val images: GiphyResponseDataImage
)

data class GiphyResponseDataImage(
    @field:SerializedName("fixed_height") val fixedHeight: ImageRendition
)

data class ImageRendition(
    @field:SerializedName("height") val height: String,
    @field:SerializedName("width") val width: String,
    @field:SerializedName("size") val size: String,
    @field:SerializedName("url") val url: String
)

data class GiphyResponsePagination(
    @field:SerializedName("total_count") val totalCount: Int,
    @field:SerializedName("count") val count: Int,
    @field:SerializedName("offset") val offset: Int
)

data class GiphyResponseMeta(
    @field:SerializedName("status") val status: Int,
    @field:SerializedName("msg") val message: String,
    @field:SerializedName("response_id") val responseId: String
)