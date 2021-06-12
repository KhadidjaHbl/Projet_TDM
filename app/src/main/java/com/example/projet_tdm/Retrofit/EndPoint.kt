package com.example.projet_tdm.Retrofit

import com.example.projet_tdm.Entities.Patient
import com.example.projet_tdm.Entities.User
import com.example.tp3.MainViewModel
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

interface EndPoint {

    @POST("login")
    fun login(@Body user: User): Call<JsonObject>

    @GET("getPatient/{idUser}")
    fun getPatient(@Path("idUser") idUser:Int): Call<List<Patient>>

    @GET("getdoctors")
    fun getMedcins(): Call<List<MainViewModel>>
}
