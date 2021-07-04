package com.example.projet_tdm.Entities

import androidx.room.ForeignKey
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*


@Entity(
    tableName = "patmed"
)
data class patmed(
    var idMedecin:Int=0,
    var idUser:Int?,
    var Date_Rdv:String=""
)


