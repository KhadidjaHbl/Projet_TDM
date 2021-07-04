package com.example.tp3

import ListTraitementsAdapter
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projet_tdm.R
import com.example.projet_tdm.Retrofit.RetrofitService
import com.example.projet_tdm.ViewModels.TraitementViewModel
import kotlinx.android.synthetic.main.fragment_traitement.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TraitementFragment : Fragment() {

    var iduser = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        return inflater.inflate(R.layout.fragment_traitement, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = this.requireActivity().getSharedPreferences("data", Context.MODE_PRIVATE)
        iduser = pref.getInt("idUser", 0)

        listTraitements.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        getTreatments()
    }

    fun getTreatments() {

        val call = RetrofitService.endpoint.getCurrentTreatments(iduser)
        call.enqueue(object : Callback<List<TraitementViewModel>> {

            override fun onResponse(
                call: Call<List<TraitementViewModel>>,
                response: Response<List<TraitementViewModel>>
            ) {
                if (response.code() == 200) {
                    val data = response.body()
                    if (data != null) {
                        val vm =
                            ViewModelProvider(requireActivity()).get(TraitementViewModel::class.java)
                        listTraitements.adapter =
                            ListTraitementsAdapter(requireActivity(), data, vm)
                    }
                } else {
                    Toast.makeText(context, "UNE ERREUR S'EST PRODUITE", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<TraitementViewModel>>, t: Throwable) {
                Toast.makeText(context, "UNE ERREUR S'EST PRODUITE", Toast.LENGTH_SHORT).show()
            }
        });
    }
}



