package com.example.projet_tdm.Entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "bookings", foreignKeys = arrayOf(
        ForeignKey(
            entity =
            User::class, parentColumns = arrayOf("idUser"),
            childColumns = arrayOf("idUser"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity =
            Specialite::class, parentColumns = arrayOf("idSpecialite"),
            childColumns = arrayOf("idSpecialite"),
            onDelete = ForeignKey.CASCADE
        )
    )
)

data class Medecin(
    val idUser: Int?,
    val nom:String?,
    val prenom:String?,
    val idSpecialite:Int?,
    val anneesExp:Int?,
    val photo:String?
)
@PrimaryKey(autoGenerate = true)
var idMedecin: Int? = null
