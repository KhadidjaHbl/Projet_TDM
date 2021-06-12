package com.example.projet_tdm.Retrofit

import com.example.projet_tdm.url
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    val endpoint: EndPoint by lazy {
        Retrofit.Builder().baseUrl(url).addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(EndPoint::class.java)
    }
}