package com.example.tp3

import CategorieAdapter
import ListMedecinsAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projet_tdm.Entities.CategorieRecyclerV
import com.example.projet_tdm.R
import com.example.projet_tdm.Retrofit.RetrofitService
import kotlinx.android.synthetic.main.fragment_list_medecins.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListMedecinsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_medecins, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listMedecins.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        getdoctors()

        var items: ArrayList<CategorieRecyclerV> = ArrayList()
        items.add(CategorieRecyclerV(R.drawable.allergie, "Allergiologie"))
        items.add(CategorieRecyclerV(R.drawable.dentist,  "Dentiste"))
        items.add(CategorieRecyclerV(R.drawable.pediatre, "Pédiaterie"))
        items.add(CategorieRecyclerV(R.drawable.neuro,    "Neurologie"))
        items.add(CategorieRecyclerV(R.drawable.radio,    "Radiologie"))
        items.add(CategorieRecyclerV(R.drawable.ophta,   "Ophtalmologie"))
        items.add(CategorieRecyclerV(R.drawable.immuno,   "Immunologie"))

        categories.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        categories.adapter=CategorieAdapter(requireActivity(),items)
    }

    fun getdoctors() {

        val call = RetrofitService.endpoint.getMedcins()
        call.enqueue(object : Callback<List<MedecinViewModel>> {

            override fun onResponse(
                call: Call<List<MedecinViewModel>>,
                response: Response<List<MedecinViewModel>>
            ) {
                if (response.code() == 200) {
                    val data = response.body()
                    if (data != null) {
                        val vm = ViewModelProvider(requireActivity()).get(MedecinViewModel::class.java)
                        listMedecins.adapter = ListMedecinsAdapter(requireActivity(), data, vm)
                    }
                } else {
                    Toast.makeText(context, "UNE ERREUR S'EST PRODUITE", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<MedecinViewModel>>, t: Throwable) {
                Toast.makeText(context, "UNE ERREUR S'EST PRODUITE", Toast.LENGTH_SHORT).show()
            }
        });

    }

}



