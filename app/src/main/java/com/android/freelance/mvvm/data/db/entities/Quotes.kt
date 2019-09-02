package com.android.freelance.mvvm.data.db.entities

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Quotes (
    @PrimaryKey(autoGenerate = false)
    val id : Int,
    val quote: String,
    val author: String,
    @Nullable
    val thumbnail: String?=null,
    val created_at: String,
    val updated_at: String
)