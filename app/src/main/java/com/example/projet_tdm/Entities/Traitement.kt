package com.example.projet_tdm.Entities

import androidx.room.ForeignKey
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*


@Entity(
    tableName = "traitement"
)
data class Traitement(
    var description: String?,
    var idUser: Int?,
    var idMedecin: Int?,
    var date_debut: Date,
    var date_fin: Date
    ) {
    @PrimaryKey(autoGenerate = true)
    var idTraitement: Int? = null
}

