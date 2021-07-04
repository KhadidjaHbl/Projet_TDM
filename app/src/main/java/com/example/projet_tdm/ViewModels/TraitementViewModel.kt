package com.example.projet_tdm.ViewModels

import androidx.lifecycle.ViewModel
import java.util.*

class TraitementViewModel : ViewModel() {
    var idMedecin: Int = 0
    var description: String = ""
    var idUser: Int = 0
    lateinit var date_debut: Date
    lateinit var date_fin: Date
    var prenom: String = ""
    var nom:String=""
    var nomSpecialite:String=""
}