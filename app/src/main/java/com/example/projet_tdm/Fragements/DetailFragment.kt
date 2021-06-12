package com.example.tp3

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.projet_tdm.R
import com.example.projet_tdm.url
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val vm = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        nomV.text = vm.nom
        prenomV.text = vm.prenom
        numV.text = vm.num_tel
        specialiteV.text = vm.nomSpecialite
        annee.text = vm.anneesExp.toString()
        Glide.with(this).load(url + "/" + vm.photo).into(pict)

        numV.setOnClickListener { view ->
            val numero = Uri.parse("tel:$numV")
            val intent = Intent(Intent.ACTION_DIAL, numero)
            startActivity(intent)
        }
    }
}