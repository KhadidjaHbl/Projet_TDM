package com.example.projet_tdm.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.projet_tdm.Entities.RdvDetails
import com.example.projet_tdm.R
import com.example.projet_tdm.ViewModels.RdvViewModel

class RdvAdapter(val context: Context, var data: List<RdvDetails>, val vm: RdvViewModel) :
    RecyclerView.Adapter<RdvAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val daterdv = view.findViewById<TextView>(R.id.date) as TextView
        val nom = view.findViewById<TextView>(R.id.nomM) as TextView
        val prenom = view.findViewById<TextView>(R.id.prenomM) as TextView
        val heurd = view.findViewById<TextView>(R.id.heure_debut) as TextView
        val heurf = view.findViewById<TextView>(R.id.heure_fin) as TextView
//        val age = view.findViewById<TextView>(R.id.Age) as TextView
        val specialite = view.findViewById<TextView>(R.id.spec) as TextView
        val rdvView = view.findViewById<ConstraintLayout>(R.id.rdvv) as ConstraintLayout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.rdv_layout, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nom.text = data[position].nommed
        holder.prenom.text = data[position].prenommed
        holder.daterdv.text = data[position].Date_Rdv.toString()
        holder.heurd.text = data[position].Heure_Debut
        holder.heurf.text = data[position].Heure_Fin
        holder.specialite.text = data[position].nomSpecialite
       // holder.age.text = data[position].Age.toString()

        holder.rdvView.setOnClickListener {
            vm.nommed = data[position].nommed
            vm.prenommed = data[position].prenommed
            vm.Date_Rdv = data[position].Date_Rdv
            vm.nomSpecialite = data[position].nomSpecialite
            vm.Heure_Debut = data[position].Heure_Debut
            vm.Heure_Fin = data[position].Heure_Fin
            vm.nomp = data[position].nomp
            vm.prenomp = data[position].prenomp
            vm.idMedecin = data[position].idMedecin
            vm.idPatient = data[position].idPatient
            vm.idRdv = data[position].idRdv
           // vm.Age = data[position].Age

            holder.rdvView.findNavController()
                .navigate(R.id.action_listrdvFragment_to_detailRdvFragment)
        }
    }
}