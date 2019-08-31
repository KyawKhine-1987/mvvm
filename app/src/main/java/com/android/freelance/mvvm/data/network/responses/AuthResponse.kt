package com.android.freelance.mvvm.data.network.responses

import com.android.freelance.mvvm.data.db.entities.User

data class AuthResponse (
    val error : Boolean ?,
    val message : String ?,
    val user : User?
)