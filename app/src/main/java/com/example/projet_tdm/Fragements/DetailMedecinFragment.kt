package com.example.tp3

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.projet_tdm.R
import com.example.projet_tdm.url
import kotlinx.android.synthetic.main.fragment_detail_medecin.*

class DetailMedecinFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_medecin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val vm = ViewModelProvider(requireActivity()).get(MedecinViewModel::class.java)
        nomV.text = vm.nom
        prenomV.text = vm.prenom
        numV.text = vm.num_tel
        specialiteV.text = vm.nomSpecialite
        annee.text = vm.anneesExp.toString()
        Glide.with(this).load(url + "/" + vm.photo).into(pict)
        val pref = this.requireActivity().getSharedPreferences("data", Context.MODE_PRIVATE)
        pref.edit {
            putInt("idMedecin",vm.idMedecin)
            Toast.makeText(context, "IDMEDDD"+vm.idMedecin, Toast.LENGTH_SHORT).show()

        }
        numV.setOnClickListener {
            val numero = Uri.parse("tel:$numV")
            val intent = Intent(Intent.ACTION_DIAL, numero)
            startActivity(intent)
        }

        conseil.setOnClickListener() {
            var bundle = bundleOf("idmedecin" to (vm.idMedecin))
            conseil.findNavController()
                .navigate(R.id.action_detailFragment_to_fragment_advice, bundle)
        }
        rdvgo.setOnClickListener(){
            rdvgo.findNavController().navigate(R.id.action_detailFragment_to_listrdvFragment)
        }

        rdv.setOnClickListener() {
            var bundle = bundleOf("idmedecin" to (vm.idMedecin))
            rdv.findNavController().navigate(R.id.action_detailFragment_to_rendezVFragment)
        }
    }
}