package com.example.projet_tdm.Fragements

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.projet_tdm.Entities.Patient
import com.example.projet_tdm.Entities.idPatient
import com.example.projet_tdm.R
import com.example.projet_tdm.Retrofit.RetrofitService
import com.example.projet_tdm.url
import kotlinx.android.synthetic.main.fragment_detail_medecin.*
import kotlinx.android.synthetic.main.fragment_listrdv.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.pict
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getPatientInfos()
    }

    fun getPatientInfos() {
        val pref = this.requireActivity().getSharedPreferences("data", Context.MODE_PRIVATE)
        val id = pref.getInt("idUser", 0)
        val call = RetrofitService.endpoint.getPatient(id)
        call.enqueue(object : Callback<List<Patient>> {
            override fun onResponse(call: Call<List<Patient>>, response: Response<List<Patient>>) {
                if (response.isSuccessful) {
                    val patient = response.body()
                    nomP.text = patient?.get(0)!!.nom
                    prenomP.text = patient?.get(0)!!.prenom
                    ageP.text = patient?.get(0)!!.Age.toString()
                    AdresseP.text = patient?.get(0)!!.Adresse
                    emailP.text =patient?.get(0)!!.Email
                    Glide.with(requireActivity()).load(url + "/" + (patient.get(0).photo)).into(pict)

                }
            }

            override fun onFailure(call: Call<List<Patient>>, t: Throwable) {
                Toast.makeText(requireActivity(), "Error", Toast.LENGTH_LONG).show();
            }
        })
    }

}