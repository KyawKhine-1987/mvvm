package com.android.freelance.mvvm.data.network

import android.util.Base64
import com.android.freelance.mvvm.data.network.responses.AuthResponse
import com.android.freelance.mvvm.data.network.responses.NetworkConnectionInterceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

const val base_url = "http://192.168.42.143/myRestApiProject/public/"

//const val url = "http://10.0.2.2/myRestApiProject/public/" it's working in emulator.
//const val url = "http://simplifiedlabs.xyz/myApi/public/"
interface MyRestApi {

    @FormUrlEncoded
    @POST("userlogin")
    suspend fun userLogIn(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<AuthResponse> //Call<ResponseBody>

    @FormUrlEncoded
    /*@POST("signup")*/
    @POST("createuser")
    suspend fun userSignUp(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<AuthResponse>

    companion object {

        //private val AUTH = "Basic " + Base64.encodeToString("brooklyn:123456".toByteArray(), Base64.NO_WRAP)
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): MyRestApi {

            /*val okHttpClient = OkHttpClient.Builder()
                 .addInterceptor{
                         chain -> val original = chain.request()
                     val requestBuilder = original.newBuilder()
                         .addHeader("Authorization", AUTH)
                         .method(original.method(), original.body())

                     val request = requestBuilder.build()
                     chain.proceed(request)
                 }.build()*/
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()


            return Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(MyRestApi::class.java)
        }
    }
}