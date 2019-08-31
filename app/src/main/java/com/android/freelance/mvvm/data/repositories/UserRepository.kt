package com.android.freelance.mvvm.data.repositories

import android.util.Log
import com.android.freelance.mvvm.data.db.entities.AppDatabase
import com.android.freelance.mvvm.data.db.entities.User
import com.android.freelance.mvvm.data.network.SafeApiRequest
import com.android.freelance.mvvm.data.network.responses.AuthResponse
import com.android.freelance.mvvm.data.network.MyRestApi

class UserRepository(
    private val api: MyRestApi,
    private val db: AppDatabase
) : SafeApiRequest() {

    private val LOG_TAG = UserRepository::class.java.name

    //LogIn Method
    suspend fun userLogIn(email: String, password: String): AuthResponse {
        Log.i(LOG_TAG, "TEST : userLogIn() is called...")

        return apiRequest { api.userLogIn(email, password) }
    }

    suspend fun saveUser(user: User) = db.userDao().upsert(user)

    fun getUser() = db.userDao().getUser()

    //SignUp Method
    suspend fun userSignUp(name: String, email: String, password: String): AuthResponse {
        Log.i(LOG_TAG, "TEST : userSignUp() is called...")

        return apiRequest { api.userSignUp(name, email, password) }
    }
}

/*suspend fun userLogIn(email: String, password: String): Response<AuthResponse> {*/
/*fun userLogIn(email: String, password: String): LiveData<String> {*/
/*val userLogInResponse = MutableLiveData<String>()

//asynchronous networking call method.
MyRestApi().userLogIn(email, password)
    .enqueue(object : Callback<ResponseBody> {
        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            Log.i(LOG_TAG, "TEST : onFailure() is called...")

            userLogInResponse.value = t.message
        }

        override fun onResponse(
            call: Call<ResponseBody>,
            response: Response<ResponseBody>
        ) {
            Log.i(LOG_TAG, "TEST : onResponse() is called...")
            if (response.isSuccessful) {

                userLogInResponse.value = response.body()?.string()
            } else {
                userLogInResponse.value = response.errorBody()?.string()
            }
        }
    })
return userLogInResponse
}*/