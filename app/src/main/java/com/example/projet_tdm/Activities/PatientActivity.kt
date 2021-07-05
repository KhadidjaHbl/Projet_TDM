package com.example.projet_tdm.Activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings.Global.putString
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.projet_tdm.Entities.Patient
import com.example.projet_tdm.R
import com.example.projet_tdm.Retrofit.RetrofitService
import com.example.tp3.ListMedecinsFragment
import com.example.tp3.TraitementFragment
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import kotlinx.android.synthetic.main.activity_patient.*
import kotlinx.android.synthetic.main.fragment_detail_medecin.*
import kotlinx.android.synthetic.main.fragment_list_medecins.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PatientActivity : AppCompatActivity() {
    var fragment: Fragment = Fragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient)

        /*bottom_nav.setOnItemSelectedListener(object :
            ChipNavigationBar.OnItemSelectedListener {
            @SuppressLint("ResourceType")
            override fun onItemSelected(i: Int) {
                when (i) {
                    R.id.home -> startActivity(Intent(this, PatientActivity::class.java))

                    R.id.traitements -> fragment = TraitementFragment()
                    //R.id.profil -> fragment = ProfileFragment()
                }


            }
        })*/

        val pref = getSharedPreferences("data", Context.MODE_PRIVATE)
        val id = pref.getInt("idUser", 0)
        val call = RetrofitService.endpoint.getPatient(id)
        call.enqueue(object : Callback<List<Patient>> {
            override fun onResponse(call: Call<List<Patient>>, response: Response<List<Patient>>) {
                if (response.isSuccessful) {
                    val nomPat = response.body()?.get(0)!!.prenom
                    val idPat = response.body()?.get(0)!!.idUser
                    pref.edit {
                        putString("patName", nomPat)
                        putInt("idPat",idPat)
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
        /////// deconexxiooooooooooooooooooooooooooon
        dec.setOnClickListener() {
            pref.edit {
                putBoolean("patConnected", false)
            }
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
            finish()
        }

        profile.setOnClickListener(){
            profile.findNavController().navigate(R.id.action_ListFragment_to_profileFragment3)
        }



    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navigation_host)
        return navController.navigateUp()
    }
}






