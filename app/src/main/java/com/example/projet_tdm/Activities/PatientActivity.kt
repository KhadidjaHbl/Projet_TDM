package com.example.projet_tdm.Activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.edit
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.example.projet_tdm.Entities.Patient
import com.example.projet_tdm.Fragements.ProfileFragment
import com.example.projet_tdm.R
import com.example.projet_tdm.Retrofit.RetrofitService
import com.example.projet_tdm.url
import kotlinx.android.synthetic.main.activity_patient.*
import kotlinx.android.synthetic.main.activity_patient.pict
import kotlinx.android.synthetic.main.fragment_detail_medecin.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PatientActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient)
        val pref = getSharedPreferences("data", Context.MODE_PRIVATE)
        val id = pref.getInt("idUser", 0)

        val navController = findNavController(R.id.navigation_host)
        bottom_nav.setupWithNavController(navController)

        toolbar.setOnMenuItemClickListener(object : MenuItem.OnMenuItemClickListener,
            Toolbar.OnMenuItemClickListener {
            override fun onMenuItemClick(item: MenuItem): Boolean {
                if (item.getItemId() == (R.id.exit)) {
                    pref.edit {
                        putBoolean("patConnected", false)
                    }
                    val loginIntent = Intent(this@PatientActivity, LoginActivity::class.java)
                    startActivity(loginIntent)
                    finish()
                }
                return false
            }
        })

        val call = RetrofitService.endpoint.getPatient(id)
        call.enqueue(object : Callback<List<Patient>> {
            override fun onResponse(call: Call<List<Patient>>, response: Response<List<Patient>>) {
                if (response.isSuccessful) {
                    val nomPat = response.body()?.get(0)!!.prenom
                    val photoPat = response.body()?.get(0)!!.photo
                    val idPat = response.body()?.get(0)!!.idUser
                    Glide.with(this@PatientActivity).load(url + "/" + (photoPat)).into(pict)
                    pref.edit {
                        putInt("idPat", idPat)
                    }
                }
            }

            override fun onFailure(call: Call<List<Patient>>, t: Throwable) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        })

    /*   pict.setOnClickListener(){
            pict.findNavController().navigate(R.id.action_HomeFragment_to_profileFragment)
        }*/
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navigation_host)
        return navController.navigateUp()
    }
}



