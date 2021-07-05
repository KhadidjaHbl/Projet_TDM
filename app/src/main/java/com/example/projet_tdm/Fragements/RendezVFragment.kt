package com.example.projet_tdm.Fragements

import android.widget.ArrayAdapter
import android.widget.CalendarView
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.projet_tdm.Entities.Rdv
import com.example.projet_tdm.Entities.Agenda
import com.example.projet_tdm.Entities.RdvPatientMed
import com.example.projet_tdm.R
import com.example.projet_tdm.Retrofit.RetrofitService
import kotlinx.android.synthetic.main.fragment_rendez_v.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat


class RendezVFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rendez_v, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //super.onActivityCreated(savedInstanceState)
        super.onViewCreated(view, savedInstanceState)

        val pref = this.requireActivity().getSharedPreferences("data", Context.MODE_PRIVATE)
        val iddoc = pref.getInt("idMedecin", 0)
        val idpat = pref.getInt("idUser", 0)
        var datardv = mutableListOf<Rdv>()
        val dataagenda = mutableListOf<Agenda>()


        Toast.makeText(context, "IDDDDDDDDD" + iddoc, Toast.LENGTH_SHORT).show()

        //commencer la requete REQUUETE DES AGENDA
        val patmed = RdvPatientMed(iddoc, idpat, "")
        val result = RetrofitService.endpoint.getAgenda(patmed)
        result.enqueue(object : Callback<List<Agenda>> {

            override fun onFailure(call: Call<List<Agenda>>, t: Throwable?) {

                Toast.makeText(context, "UNE ERREUR S'EST PRODUITE agenda", Toast.LENGTH_SHORT)
                    .show()


            }

            override fun onResponse(call: Call<List<Agenda>>, response: Response<List<Agenda>>) {

                if (response.isSuccessful) {

                    val data = response.body()

                    if (data != null) {
                        Toast.makeText(context, "L AGENDA EST pas NULLLLL", Toast.LENGTH_SHORT)
                            .show()

                        for (item in data) {
                            dataagenda.add(item)
                            Toast.makeText(context, item.Jour.toString(), Toast.LENGTH_SHORT).show()

                        }

                    } else {
                        Toast.makeText(context, "L AGENDA EST NULLLLL", Toast.LENGTH_SHORT).show()
                    }

                } else {
                    Toast.makeText(context, "Erreur Réseau", Toast.LENGTH_SHORT).show()

                }
            }

        })

        // fin requete


        // Toast.makeText(context, "LE JOUR DU MEDCIN"+dataagenda[0].Jour.toString(), Toast.LENGTH_SHORT).show()

        val sdf = SimpleDateFormat("yy/MM/dd")

        var datenet = Date(calendarViewr.date)
        var datesel = sdf.format(datenet)
        calendarViewr.setOnDateChangeListener(object : CalendarView.OnDateChangeListener {
            @RequiresApi(Build.VERSION_CODES.O)

            override fun onSelectedDayChange(p0: CalendarView, p1: Int, p2: Int, p3: Int) {
                Toast.makeText(context, "datechanged", Toast.LENGTH_SHORT).show()
                val d = mutableListOf<Rdv>()
                var heurdmed = ""
                var heurfmed = ""
                datardv = d
                var mois = ""
                var jour = ""
                if ((p2 + 1).toInt() < 10) {
                    mois = "0"
                }
                if ((p3).toInt() < 10) {
                    jour = "0"
                }


                datesel = "$p1-$mois${p2 + 1}-$jour$p3"

                var simpleFormat = DateTimeFormatter.ISO_DATE;
                var convertedDate = LocalDate.parse(datesel, simpleFormat)

                var joursel = convertedDate.dayOfWeek.toString()
                if (joursel.equals("SATURDAY")) {
                    joursel = "samedi"
                }
                if (joursel.equals("SUNDAY")) {
                    joursel = "dimanche"
                }
                if (joursel.equals("MONDAY")) {
                    joursel = "lundi"
                }
                if (joursel.equals("TUESDAY")) {
                    joursel = "mardi"
                }
                if (joursel.equals("WEDNESDAY")) {
                    joursel = "mercredi"
                }
                if (joursel.equals("THURSDAY")) {
                    joursel = "jeudi"
                }
                if (joursel.equals("FRIDAY")) {
                    joursel = "vendredi"
                }
                //ICIIII LE TRAITEMENT SUR L AGENA DU MEDCIN
                var add2 = 0
                for (ag in dataagenda) {
                    if (joursel.equals(ag.Jour)) {
                        add2 = 1
                        heurdmed = ag.temps_matin_debut
                        heurfmed = ag.temps_soir_fin

                    }
                }

                //val basehorag= mutableListOf<String>("08:00","08:30","09:00","09:30","10:00","10:30","11:00","11:30","13:00","13:30","14:00","14:30","15:00","15:30","16:00","16:30")


                Toast.makeText(context, datesel, Toast.LENGTH_SHORT).show()
                Toast.makeText(context, joursel.toString(), Toast.LENGTH_SHORT).show()
                var patmed = RdvPatientMed(iddoc, 0, datesel)

                //REQUETE DES RDVS
                var re = RetrofitService.endpoint.getRdv(patmed)
                re.enqueue(object : Callback<List<Rdv>> {

                    override fun onFailure(call: Call<List<Rdv>>, t: Throwable?) {

                        Toast.makeText(context, "UNE ERREUR S'EST PRODUITE RDV", Toast.LENGTH_SHORT)
                            .show()


                    }

                    override fun onResponse(call: Call<List<Rdv>>, response: Response<List<Rdv>>) {

                        if (response.isSuccessful) {

                            val data = response.body()

                            if (data != null) {
                                for (item in data) {
                                    datardv.add(item)

                                }

                                var add = 1

                                val basehor = mutableListOf<String>(
                                    "08:00",
                                    "08:30",
                                    "09:00",
                                    "09:30",
                                    "10:00",
                                    "10:30",
                                    "11:00",
                                    "11:30",
                                    "13:00",
                                    "13:30",
                                    "14:00",
                                    "14:30",
                                    "15:00",
                                    "15:30",
                                    "16:00",
                                    "16:30"
                                )
                                val l = mutableListOf<String>()
                                for (hor in basehor) {
                                    if (datardv.size != 0) {
                                        for (rdv in datardv) {
                                            //Toast.makeText(context, rdv.heurdrdv, Toast.LENGTH_SHORT).show()
                                            var horint = hor.replace(":", "0").toInt()
                                            var heurdrdvint =
                                                rdv.Heure_Debut.replace(":", "0").toInt()
                                            var heurfrdvint =
                                                rdv.Heure_Fin.replace(":", "0").toInt()

                                            // if(hor.equals(rdv.heurdrdv) )
                                            if ((horint >= heurdrdvint) and (horint <= heurfrdvint)) {
                                                add = 0
                                            }
                                            //ici la comparaison avec l agenda selon le jour selectionne

                                        }
                                    }
                                    var add3 = 0
                                    if (add2 == 1) {
                                        var horagint = hor.replace(":", "0").toInt()
                                        var heurdagint = heurdmed.replace(":", "0").toInt()
                                        var heurfagint = heurfmed.replace(":", "0").toInt()
                                        if ((horagint >= heurdagint) and (horagint <= heurfagint)) {
                                            add3 = 1
                                        }
                                    }

                                    if ((add == 1) and (add2 == 1) and (add3 == 1)) {
                                        l.add(hor)
                                    }
                                    add = 1
                                }
                                val adapter = ArrayAdapter(
                                    requireContext(),
                                    android.R.layout.simple_spinner_item,
                                    l
                                )
                                spinner.adapter = adapter

                            }

                        } else {
                            Toast.makeText(context, "Erreur Réseau", Toast.LENGTH_SHORT).show()


                        }
                    }

                })

                // fin requete


            }
        })


        reserv.setOnClickListener {
            if (spinner.selectedItem != null) {
                var heurdrdv = spinner.selectedItem.toString()
                val basehor = mutableListOf<String>(
                    "08:00",
                    "08:30",
                    "09:00",
                    "09:30",
                    "10:00",
                    "10:30",
                    "11:00",
                    "11:30",
                    "13:00",
                    "13:30",
                    "14:00",
                    "14:30",
                    "15:00",
                    "15:30",
                    "16:00",
                    "16:30"
                )
                var bool = 0
                var heurfrdv = ""
                for (ho in basehor) {
                    if (bool == 1) {
                        heurfrdv = ho
                        bool = 0
                    }
                    if (ho.equals(heurdrdv)) {
                        bool = 1
                    }
                }
                // val rdv=Rdv(0,iddoc,datesel,heurdrdv,heurfrdv,idpat)
                val rdv = Rdv(0, iddoc, idpat, heurdrdv, heurfrdv, datesel)
                val result = RetrofitService.endpoint.addrdv(rdv)
                result.enqueue(object : Callback<String> {

                    override fun onFailure(call: Call<String>, t: Throwable?) {

                        Toast.makeText(
                            context,
                            "UNE ERREUR S'EST PRODUITE agenda",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onResponse(call: Call<String>, response: Response<String>) {

                        if (response.isSuccessful) {

                            val data = response.body()
                            Toast.makeText(context, "Rendez Vous Reservé", Toast.LENGTH_SHORT)
                                .show()
                            if (data != null) {
                            }

                        } else {
                            Toast.makeText(context, "Erreur Réseau", Toast.LENGTH_SHORT).show()
                        }
                    }
                })

            } else {
                Toast.makeText(context, "Pas d heure disponible", Toast.LENGTH_SHORT).show()
            }
        }
    }

}



