package com.android.freelance.mvvm.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.freelance.mvvm.R
import com.android.freelance.mvvm.data.db.entities.User
import com.android.freelance.mvvm.databinding.ActivitySignupBinding
import com.android.freelance.mvvm.ui.callback_bridge.AuthListener
import com.android.freelance.mvvm.ui.home.HomeActivity
import com.android.freelance.mvvm.ui.viewmodels.AuthViewModel
import com.android.freelance.mvvm.ui.viewmodels.AuthViewModelFactory
import com.android.freelance.mvvm.util.hide
import com.android.freelance.mvvm.util.show
import com.android.freelance.mvvm.util.snackbar
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import kotlinx.android.synthetic.main.activity_signup.*

class SignUpActivity : AppCompatActivity(), AuthListener , KodeinAware {

    private val LOG_TAG = SignUpActivity::class.java.name

    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(LOG_TAG, "TEST : onCreate() is called...")

        super.onCreate(savedInstanceState)
        val binding: ActivitySignupBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_signup
        )// That ActivityLoginBinding class is automatically generated.

        val mSignUpViewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = mSignUpViewModel
        mSignUpViewModel.authListener = this

        mSignUpViewModel.getLoggedInUser().observe(this, Observer { user ->
            if (user != null) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })
    }

    override fun onStarted() {
        Log.i(LOG_TAG, "TEST : onStarted() is called...")

        pbSignUp_Loading.show()
    }

    override fun onSuccess(user: User) {
        Log.i(LOG_TAG, "TEST : onSuccess() is called...")

        pbSignUp_Loading.hide()
        //root_layout.snackbar("${user.name} is Logged In.")
        /*toast("${user.name} is Logged In.")*/
    }

    override fun onFailure(message: String) {
        Log.i(LOG_TAG, "TEST : onFailure() is called...")

        pbSignUp_Loading.hide()
        root_layout.snackbar(message)
        /*toast(message)*/
    }
}
