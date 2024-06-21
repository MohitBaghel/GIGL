package com.system.gigl.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "shorts")
data class Shorts(
    @PrimaryKey val id: Int,
    val title: String,
    val thumbnailUrl: String
)

