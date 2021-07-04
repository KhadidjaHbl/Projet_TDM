package com.example.projet_tdm.Activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.edit
import com.example.projet_tdm.Entities.Patient
import com.example.projet_tdm.R
import com.example.projet_tdm.Retrofit.RetrofitService
import kotlinx.android.synthetic.main.fragment_list_medecins.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TreatmentsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_treatments)

        val pref = getSharedPreferences("data", Context.MODE_PRIVATE)
        val id = pref.getInt("idUser", 0)
        val call = RetrofitService.endpoint.getPatient(id)
        call.enqueue(object : Callback<List<Patient>> {
            override fun onResponse(call: Call<List<Patient>>, response: Response<List<Patient>>) {
                if (response.isSuccessful) {
                    val nomPat = response.body()?.get(0)!!.prenom
                    pref.edit {
                        putString("patName", nomPat)
                    }
                }
            }

            override fun onFailure(call: Call<List<Patient>>, t: Throwable) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        })
        val conn = pref.getBoolean("patConnected", false)
        if (conn) {
            prenomP.text = pref.getString("patName", "")
        }
    }
}