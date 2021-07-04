package com.example.tp3

import androidx.lifecycle.ViewModel

class MedecinViewModel:ViewModel() {
     var idMedecin : Int = 0
     var nom: String=""
     var prenom: String=""
     var num_tel: String=""
     var nomSpecialite: String=""
     var photo: Number=0
     var anneesExp: Number=0
     var longitude:Float = 0.0f
     var latitude:Float=0.0f
}