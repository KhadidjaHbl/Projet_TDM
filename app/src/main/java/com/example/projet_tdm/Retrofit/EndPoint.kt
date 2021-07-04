package com.example.projet_tdm.Retrofit

import com.example.projet_tdm.Entities.*
import com.example.projet_tdm.ViewModels.TraitementViewModel
import com.example.tp3.MedecinViewModel
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

interface EndPoint {

    @POST("login")
    fun login(@Body user: User): Call<JsonObject>

    @GET("getPatient/{idUser}")
    fun getPatient(@Path("idUser") idUser: Int): Call<List<Patient>>

    @GET("getdoctors")
    fun getMedcins(): Call<List<MedecinViewModel>>

    @GET("getCurrentTreatments/{idUser}")
    fun getCurrentTreatments(@Path("idUser") idUser: Int): Call<List<TraitementViewModel>>

    @POST("addConseil")
    fun addConseil(@Body conseil: Conseil): Call<String>

    @POST("addConseils")
    fun addConseils(@Body teams: List<Conseil>): Call<String>

    @POST("getRdv")
    fun getRdv(@Body patmed: patmed): Call<List<rdv>>

    @POST("getAgenda")
    fun getAgenda(@Body patmed: patmed): Call<List<agenda>>

    @POST("addrdv")
    fun addrdv(@Body rdv: rdv): Call<String>

    @POST("getmyrdvs")
    fun getmyrdvs(@Body patmed: patmed): Call<List<infordv>>

}
