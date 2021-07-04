package com.example.projet_tdm.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.projet_tdm.Entities.infordv
import com.example.projet_tdm.R
import com.example.projet_tdm.ViewModels.RdvViewModel


//import com.example.url

class RdvAdapter(val context: Context, var data: List<infordv>, val vm: RdvViewModel) :
    RecyclerView.Adapter<RdvAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val daterdv = view.findViewById<TextView>(R.id.textViewdaterdv) as TextView
        val nom = view.findViewById<TextView>(R.id.nomMed) as TextView
        val prenom = view.findViewById<TextView>(R.id.prenomMed) as TextView
        val heurd = view.findViewById<TextView>(R.id.textViewheurd) as TextView
        val heurf = view.findViewById<TextView>(R.id.textViewheurf) as TextView
        val specialite = view.findViewById<TextView>(R.id.textViewspecrd) as TextView
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

        //holder.photo.setImageResource(data[position].photo)
        //Glide.with(context).load("https://c6a57e250331.ngrok.io/"+data[position].photo).into(holder.photo)

        // Toast.makeText(context, url+data[position].photo, Toast.LENGTH_SHORT).show()

        /*    holder.num.setOnClickListener { view ->
                val numTel = data[position].num_tel
                val numero = Uri.parse("tel:$numTel")
                val intent = Intent(Intent.ACTION_DIAL, numero)
                context.startActivity(intent)
            }
    //Ici mettre la fonction de l ittiniraire
            holder.icon.setOnClickListener { view ->
                val latitude = data[position].latitude
                val longitude = data[position].longitude
                val geoLocation = Uri.parse("geo:$latitude,$longitude")
                val intent = Intent(Intent.ACTION_VIEW, geoLocation)
                context.startActivity(intent)
            }*/

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





            holder.rdvView.findNavController()
                .navigate(R.id.action_listrdvFragment_to_detailRdvFragment)
        }
    }
}