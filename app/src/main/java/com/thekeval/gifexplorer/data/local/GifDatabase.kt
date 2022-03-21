package com.thekeval.gifexplorer.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.thekeval.gifexplorer.utilities.DATABASE_NAME

@Database(entities = [GifEntity::class], version = 1, exportSchema = false)
abstract class GifDatabase : RoomDatabase() {

    abstract fun gifDao(): GifDao

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: GifDatabase? = null

        fun getInstance(context: Context): GifDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): GifDatabase {
            return Room.databaseBuilder(context, GifDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }

    }

}