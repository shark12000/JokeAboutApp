package com.example.jokeaboutapp.data

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("jokes/random")
    fun fetch(@Query("firstName") firstName: String?,
              @Query("lastName") lastName: String?
    ) : Call<String>

    companion object {
        fun create() : Api {
            val retrofit = Retrofit.Builder()
                    .baseUrl(" http://api.icndb.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(OkHttpClient.Builder().build())
                    .build()

            return retrofit.create(Api::class.java)
        }
    }
}