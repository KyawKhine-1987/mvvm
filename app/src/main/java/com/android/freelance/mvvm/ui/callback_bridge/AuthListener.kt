package com.android.freelance.mvvm.ui.callback_bridge

import androidx.lifecycle.LiveData
import com.android.freelance.mvvm.data.db.entities.User

interface AuthListener {

    fun onStarted()
    /*fun onSuccess(userLogInResponse: LiveData<String>)*/
    fun onSuccess(user: User)
    fun onFailure(message: String)
}