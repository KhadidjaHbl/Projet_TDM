package com.example.projet_tdm.Entities

import androidx.room.ForeignKey
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*


@Entity(
    tableName = "agenda"
)
data class agenda(
    var idMedecin:Int=0,
    var temps_matin_debut:String="",
    var temps_matin_fin:String="",
    var temps_soir_debut:String="",
    var temps_soir_fin:String="",
    var Jour:String=""
)


