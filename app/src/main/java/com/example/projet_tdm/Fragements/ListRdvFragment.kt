package com.example.projet_tdm.Fragements

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projet_tdm.Adapters.RdvAdapter
import com.example.projet_tdm.Entities.idPatient
import com.example.projet_tdm.Entities.RdvDetails
import com.example.projet_tdm.Entities.RdvPatientMed
import com.example.projet_tdm.R
import com.example.projet_tdm.Retrofit.RetrofitService
import com.example.projet_tdm.ViewModels.RdvViewModel
import kotlinx.android.synthetic.main.fragment_listrdv.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListRdvFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_listrdv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = this.requireActivity().getSharedPreferences("data", Context.MODE_PRIVATE)
        idPatient = pref.getInt("idUser", 0)

        listRdv.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        getRdvs()
    }

    fun getRdvs() {
        val patmed = RdvPatientMed(0, idPatient, "")
        Toast.makeText(context, "piattttt"+ idPatient, Toast.LENGTH_SHORT).show()
        val call = RetrofitService.endpoint.getmyrdvs(patmed)
        call.enqueue(object : Callback<List<RdvDetails>> {

            override fun onResponse(
                call: Call<List<RdvDetails>>,
                response: Response<List<RdvDetails>>
            ) {
                if (response.code() == 200) {
                    val data = response.body()
                    if (data != null) {
                        val vm =
                            ViewModelProvider(requireActivity()).get(RdvViewModel::class.java)
                        listRdv.adapter =
                            RdvAdapter(requireActivity(), data, vm)
                    }
                    else{
                        Toast.makeText(context, "La liste est vide ", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "UNE ERREUR S'EST PRODUITE else", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<RdvDetails>>, t: Throwable) {
                Toast.makeText(context, "UNE ERREUR S'EST PRODUITE", Toast.LENGTH_SHORT).show()
            }
        });
    }
}
