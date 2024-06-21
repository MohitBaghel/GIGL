package com.system.gigl.db

import ShortDao
import VideoDao
import androidx.room.Database
import androidx.room.RoomDatabase
import com.system.gigl.models.Shorts
import com.system.gigl.models.Videos

@Database(entities = [Videos::class, Shorts::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun videoDao(): VideoDao
    abstract fun shortDao(): ShortDao
}