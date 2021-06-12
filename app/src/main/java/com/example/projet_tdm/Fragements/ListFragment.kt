package com.example.tp3

import CategorieAdapter
import MyAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projet_tdm.Entities.CategorieRecyclerV
import com.example.projet_tdm.R
import com.example.projet_tdm.Retrofit.RetrofitService
import kotlinx.android.synthetic.main.fragment_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /* val pref = this.requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE)
         val id = pref.getInt("idUser",0)
         val call = RetrofitService.endpoint.getPatient(id)
         Toast.makeText(context, "calll"+ call.toString(),Toast.LENGTH_LONG).show();

         call.enqueue(object : Callback<Patient> {
             override fun onResponse(call: Call<Patient>, response: Response<Patient>) {
                 if (response.isSuccessful) {
                     val nomPat = response.body()?.nom
                     welcome.text = nomPat
                 }
             }
             override fun onFailure(call: Call<Patient>, t: Throwable) {
                 Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
             }
         })*/

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
        call.enqueue(object : Callback<List<MainViewModel>> {

            override fun onResponse(
                call: Call<List<MainViewModel>>,
                response: Response<List<MainViewModel>>
            ) {
                if (response.code() == 200) {
                    val data = response.body()
                    if (data != null) {
                        val vm = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
                        listMedecins.adapter = MyAdapter(requireActivity(), data, vm)
                    }
                } else {

                }
            }

            override fun onFailure(call: Call<List<MainViewModel>>, t: Throwable) {
                Toast.makeText(context, "UNE ERREUR S'EST PRODUITE", Toast.LENGTH_SHORT).show()
            }

        });


    }

}



