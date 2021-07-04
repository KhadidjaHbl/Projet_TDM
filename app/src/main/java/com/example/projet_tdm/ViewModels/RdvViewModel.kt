package com.example.projet_tdm.ViewModels

import androidx.lifecycle.ViewModel
import java.util.*

class RdvViewModel : ViewModel() {
    var idRdv:Int=0
    var idMedecin:Int=0
    var idPatient:Int=0
    var Heure_Debut:String=""
    var Heure_Fin:String=""
    var Date_Rdv:String=""
    var nomp:String=""
    var prenomp:String=""
    var nommed:String=""
    var prenommed:String=""
    var nomSpecialite:String=""
}