package com.android.freelance.mvvm.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.freelance.mvvm.R

class SignUpActivity : AppCompatActivity() {

    private val LOG_TAG = SignUpActivity::class.java.name

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(LOG_TAG, "TEST : onCreate() is called...")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
    }
}
