package com.thekeval.gifexplorer.data.models

import com.google.gson.annotations.SerializedName

data class TrendingResponse(
    @field:SerializedName("data") val data: List<TrendingResponseData>,
    @field:SerializedName("pagination") val pagination: TrendingResponsePagination,
    @field:SerializedName("meta") val meta: TrendingResponseMeta
)

data class TrendingResponseData(
    @field:SerializedName("id") val id: String,
    @field:SerializedName("images") val images: TrendingResponseDataImage
)

data class TrendingResponseDataImage(
    @field:SerializedName("fixed_height") val fixedHeight: ImageRendition
)

data class ImageRendition(
    @field:SerializedName("height") val height: String,
    @field:SerializedName("width") val width: String,
    @field:SerializedName("size") val size: String,
    @field:SerializedName("url") val url: String
)

data class TrendingResponsePagination(
    @field:SerializedName("total_count") val totalCount: Int,
    @field:SerializedName("count") val count: Int,
    @field:SerializedName("offset") val offset: Int
)

data class TrendingResponseMeta(
    @field:SerializedName("status") val status: Int,
    @field:SerializedName("msg") val message: String,
    @field:SerializedName("response_id") val responseId: String
)