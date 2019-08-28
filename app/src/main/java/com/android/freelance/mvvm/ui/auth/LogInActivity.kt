package com.android.freelance.mvvm.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.android.freelance.mvvm.R
import com.android.freelance.mvvm.databinding.ActivityLoginBinding
import com.android.freelance.mvvm.ui.callback_bridge.AuthListener
import com.android.freelance.mvvm.ui.viewmodels.AuthViewModel
import com.android.freelance.mvvm.util.toast

class LogInActivity : AppCompatActivity(), AuthListener {
    private val LOG_TAG = LogInActivity::class.java.name

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(LOG_TAG, "TEST : onCreate() is called...")

        super.onCreate(savedInstanceState)//setContentView(R.layout.activity_login)
        val binding : ActivityLoginBinding  = DataBindingUtil.setContentView(
            this,
            R.layout.activity_login
        )// That ActivityLoginBinding class is automatically generated.
        val viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this
    }

    override fun onStarted() {
        Log.i(LOG_TAG, "TEST : onStarted() is called...")
        toast("LogIn Started.")
    }

    override fun onSuccess() {
        Log.i(LOG_TAG, "TEST : onSuccess() is called...")
        toast("LogIn Success.")
    }

    override fun onFailure(message: String) {
        Log.i(LOG_TAG, "TEST : onFailure() is called...")
        toast(message)
    }
}
