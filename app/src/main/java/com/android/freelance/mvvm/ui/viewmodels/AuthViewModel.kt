package com.android.freelance.mvvm.ui.viewmodels

import android.view.View
import androidx.lifecycle.ViewModel
import com.android.freelance.mvvm.ui.callback_bridge.AuthListener

class AuthViewModel : ViewModel() {

    var email: String? = null
    var password: String? = null
    var authListener: AuthListener? = null

    fun onBtnLogInClick(view: View) {

        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invaild email or password!")
            return
        }
        //success
        authListener?.onSuccess()
    }

    fun onTextSignUp(view: View){

    }
}