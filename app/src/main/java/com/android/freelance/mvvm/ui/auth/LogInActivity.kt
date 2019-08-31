package com.android.freelance.mvvm.ui.auth

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.freelance.mvvm.R
import com.android.freelance.mvvm.data.db.entities.User
import com.android.freelance.mvvm.databinding.ActivityLoginBinding
import com.android.freelance.mvvm.ui.callback_bridge.AuthListener
import com.android.freelance.mvvm.ui.home.HomeActivity
import com.android.freelance.mvvm.ui.viewmodels.AuthViewModel
import com.android.freelance.mvvm.ui.viewmodels.AuthViewModelFactory
import com.android.freelance.mvvm.util.hide
import com.android.freelance.mvvm.util.show
import com.android.freelance.mvvm.util.snackbar
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.android.kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class LogInActivity : AppCompatActivity(), AuthListener, KodeinAware {
    private val LOG_TAG = LogInActivity::class.java.name

    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(LOG_TAG, "TEST : onCreate() is called...")

        super.onCreate(savedInstanceState)//setContentView(R.layout.activity_login)

        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_login
        )// That ActivityLoginBinding class is automatically generated.

        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this

        viewModel.getLoggedInUser().observe(this, Observer { user ->
            if (user != null) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })
    }

    override fun onStarted() {
        Log.i(LOG_TAG, "TEST : onStarted() is called...")

        pbLogIn_Loading.show()
    }

    override fun onSuccess(user: User) {
        Log.i(LOG_TAG, "TEST : onSuccess() is called...")

        pbLogIn_Loading.hide()
        //root_layout.snackbar("${user.name} is Logged In.")
        /*toast("${user.name} is Logged In.")*/
    }

    override fun onFailure(message: String) {
        Log.i(LOG_TAG, "TEST : onFailure() is called...")

        pbLogIn_Loading.hide()
        root_layout.snackbar(message)
        /*toast(message)*/
    }
}

/* val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        val api = MyRestApi(networkConnectionInterceptor)
        val db = AppDatabase(this)
        val repository = UserRepository(api, db)
        val factory = AuthViewModelFactory(repository)*/

/*override fun onSuccess(userLogInResponse: LiveData<String>) {
       Log.i(LOG_TAG, "TEST : onSuccess() is called...")

       userLogInResponse.observe(this, Observer {
           pb_Loading.hide()
           toast(it)
       })
   }*/
