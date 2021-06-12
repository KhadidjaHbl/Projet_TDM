package com.example.projet_tdm.Activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.edit
import androidx.navigation.findNavController
import com.example.projet_tdm.Entities.Patient
import com.example.projet_tdm.R
import com.example.projet_tdm.Retrofit.RetrofitService
import kotlinx.android.synthetic.main.fragment_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PatientHome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_home)
        val pref = getSharedPreferences("data", Context.MODE_PRIVATE)

        val id = pref.getInt("idUser", 0)
        Toast.makeText(getApplicationContext(), "iddd" + id, Toast.LENGTH_LONG).show()
        val call = RetrofitService.endpoint.getPatient(id)
        call.enqueue(object : Callback<List<Patient>> {
            override fun onResponse(call: Call<List<Patient>>, response: Response<List<Patient>>) {
                if (response.isSuccessful) {
                    val nomPat = response.body()?.get(0)!!.prenom
                    prenomP.text = nomPat
                }
            }

            override fun onFailure(call: Call<List<Patient>>, t: Throwable) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        })

///////////////// deconexxiooooooooooooooooooooooooooon
        dec.setOnClickListener() {
            pref.edit {
                putBoolean("patConnected", false)
            }
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
            finish()
        }
        val nav = findNavController(R.id.nav_host)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host)
        return navController.navigateUp()
    }

}

