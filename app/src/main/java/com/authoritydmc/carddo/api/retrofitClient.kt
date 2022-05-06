package com.authoritydmc.carddo.api

import com.authoritydmc.carddo.utility.UTILS
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object retrofitClient {

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()
                .method(original.method(), original.body())

            val request = requestBuilder.build()
            chain.proceed(request)
        }.build()

    val instance: api by lazy{
        val retrofit = Retrofit.Builder()
            .baseUrl(UTILS.UPDATE_CHECK_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(api::class.java)
    }

}