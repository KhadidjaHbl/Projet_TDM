package com.example.projet_tdm.Entities

import androidx.room.ForeignKey
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*


@Entity(
    tableName = "rdv"
)
data class Rdv(
    var idRdv:Int =0,
    var idMedecin:Int=0,
    var idPatient:Int,
    var Heure_Debut:String="",
    var Heure_Fin:String="",
    var Date_Rdv:String
)


