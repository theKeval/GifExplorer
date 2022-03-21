package com.thekeval.gifexplorer.di

import android.content.Context
import com.thekeval.gifexplorer.data.local.GifDao
import com.thekeval.gifexplorer.data.local.GifDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideGifDatabase(@ApplicationContext context: Context): GifDatabase {
        return GifDatabase.getInstance(context)
    }

    @Provides
    fun provideGifDao(gifDatabase: GifDatabase): GifDao {
        return gifDatabase.gifDao()
    }

}