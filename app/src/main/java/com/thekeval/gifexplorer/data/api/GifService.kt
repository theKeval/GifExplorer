package com.thekeval.gifexplorer.data.api

import com.thekeval.gifexplorer.BuildConfig
import com.thekeval.gifexplorer.data.models.TrendingResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface GifService {

    @GET("trending")
    suspend fun getTrending(
        @Query("limit")
        limit: Int = 25,
        @Query("offset")
        offset: Int = 0,
        @Query("api_key")
        apiKey: String = BuildConfig.GIPHY_API_KEY,
    ): TrendingResponse

    companion object {
        private const val BASE_URL = "https://api.giphy.com/v1/gifs/"

        fun create(): GifService {
            val logger = HttpLoggingInterceptor().apply { level = Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GifService::class.java)

        }
    }

}