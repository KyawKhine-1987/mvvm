package com.android.freelance.mvvm.data.network

import android.util.Log
import com.android.freelance.mvvm.util.ApiException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

abstract class SafeApiRequest {

    private val LOG_TAG = SafeApiRequest::class.java.name

    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
        Log.i(LOG_TAG, "TEST : apiRequest() is called...")

        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val error = response.errorBody()?.string()
            val message = StringBuilder()
            error?.let {
                try {
                    message.append(JSONObject(it).getString("message"))
                } catch (e: JSONException) {
                }
                message.append("\n")
            }
            message.append("Error Code :  ${response.code()}")
            throw ApiException(message.toString())
        }
    }
}
