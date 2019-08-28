package com.android.freelance.mvvm.ui.callback_bridge

interface AuthListener {

    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)
}