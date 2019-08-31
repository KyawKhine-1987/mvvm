package com.android.freelance.mvvm.ui.viewmodels

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.android.freelance.mvvm.data.repositories.UserRepository
import com.android.freelance.mvvm.ui.auth.LogInActivity
import com.android.freelance.mvvm.ui.auth.SignUpActivity
import com.android.freelance.mvvm.ui.callback_bridge.AuthListener
import com.android.freelance.mvvm.util.ApiException
import com.android.freelance.mvvm.util.Coroutines
import com.android.freelance.mvvm.util.NoInternetException

class AuthViewModel(
    private val repository: UserRepository
) : ViewModel() {

    private val LOG_TAG = AuthViewModel::class.java.name
    var name: String? = null
    var email: String? = null
    var password: String? = null
    var passwordConfirm: String? = null
    var authListener: AuthListener? = null

    /*LogIn Method*/
    fun onBtnLogInClick(view: View) {
        Log.i(LOG_TAG, "TEST : onBtnLogInClick() is called...")

        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid email or password!")
            return
        }

        //success
        Coroutines.main {
            try {
                val authResponse = repository.userLogIn(email!!, password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            } catch (e: ApiException) {
                authListener?.onFailure(e.message!!)
            } catch (e: NoInternetException) {
                authListener?.onFailure(e.message!!)
            }
        }
    }

    fun onTextLogIn(view: View){
        Log.i(LOG_TAG, "TEST : onTextLogIn() is called...")

        Intent(view.context, LogInActivity::class.java).also{
            view.context.startActivity(it)
        }
    }

    fun getLoggedInUser() = repository.getUser()

    /*SignUp Method*/
    fun onBtnSignUpClick(view: View) {
        Log.i(LOG_TAG, "TEST : onBtnSignUpClick() is called...")

        authListener?.onStarted()

        if (name.isNullOrEmpty()) {
            authListener?.onFailure("Name is required!")
            return
        }

        if (email.isNullOrEmpty()) {
            authListener?.onFailure("Email is required!")
            return
        }

        if (password.isNullOrEmpty()) {
            authListener?.onFailure("Please, enter a password!")
            return
        }

        if (password != passwordConfirm) {
            authListener?.onFailure("Password didn't match!")
            return
        }

        //success
        Coroutines.main {
            try {
                val authResponse = repository.userSignUp(name!!, email!!, password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            } catch (e: ApiException) {
                authListener?.onFailure(e.message!!)
            } catch (e: NoInternetException) {
                authListener?.onFailure(e.message!!)
            }
        }
    }

    fun onTextSignUp(view: View) {
        Log.i(LOG_TAG, "TEST : onTextSignUp() is called...")

        Intent(view.context, SignUpActivity::class.java).also{
            view.context.startActivity(it)
        }
    }
}

//success
/*val userLogInResponse = UserRepository().userLogIn(email!!, password!!)
        authListener?.onSuccess(userLogInResponse)*/

/* Coroutines.main {
     val response = UserRepository().userLogIn(email!!, password!!)
     if(response.isSuccessful){
         authListener?.onSuccess(response.body()?.user!!)
     }else{
         authListener?.onFailure("Error Code : ${response.code()}")
     }
 }*/