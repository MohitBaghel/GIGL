package com.system.gigl.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "videos")
data class Videos(
    @PrimaryKey val id: Int,
    val title: String,
    val thumbnailUrl: String
)
