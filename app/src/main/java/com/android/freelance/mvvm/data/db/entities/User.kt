package com.android.freelance.mvvm.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

const val Current_UserId = 0

@Entity
data class User(
    var userId: Int? = null,
    var name: String? = null,
    var email: String? = null,
    var school: String? = null,
    var created_at: String? = null,
    var updated_at: String? = null
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = Current_UserId
}