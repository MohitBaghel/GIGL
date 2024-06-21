package com.system.gigl.repo

import android.content.Context
import androidx.room.Room
import com.system.gigl.db.AppDatabase
import com.system.gigl.models.Shorts
import com.system.gigl.models.Videos
import com.system.gigl.network.RetrofitInstance
import com.system.gigl.utils.Result


class VideoRepository(context : Context) {

//    private val db = Room.databaseBuilder(context = context.applicationContext,AppDatabase::class.java, "app_database").build()


    suspend fun getVideos(): Result<List<Videos>> {
        return try {
            val response = RetrofitInstance.api.getVideos()
            if (response.isSuccessful) {
                response.body()?.let {  videos ->
//                    db.videoDao().insertAll(videos)
                    Result.Success(videos)
                } ?: Result.Error("Error occurred: ${"No data Available"}")
            } else {
                Result.Error("Error occurred: ${response.errorBody()?.string()}")
            }
        } catch (e: Exception) {
//            val cachedVideos = db.videoDao().getAllVideos()
//            if (cachedVideos.isNotEmpty()) {
//                Result.Success(cachedVideos)
//            } else {
                Result.Error("Error: ${e.message}")
//            }
        }
    }

    suspend fun getShorts(): Result<List<Shorts>> {
        return try {
            val response = RetrofitInstance.api.getShorts()
            if (response.isSuccessful) {
                response.body()?.let { shorts ->
//                    db.shortDao().insertAll(shorts)
                    Result.Success(shorts)
                } ?: Result.Error("No data available")
            } else {
                Result.Error("Error occurred: ${response.errorBody()?.string()}")
            }
        } catch (e: Exception) {
//            val cachedShorts = db.shortDao().getAllShorts()
//            if (cachedShorts.isNotEmpty()) {
//                Result.Success(cachedShorts)
//            } else {
                Result.Error("Error: ${e.message}")
//            }
        }
    }
}