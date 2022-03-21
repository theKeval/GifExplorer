package com.thekeval.gifexplorer.di

import com.thekeval.gifexplorer.data.api.GifService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideGifService(): GifService {
        return GifService.create()
    }
}