package com.example.projet_tdm.Entities

import android.icu.text.Transliterator
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "doctors", foreignKeys = arrayOf(
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
        ),
        ForeignKey(
            entity =
            Position::class, parentColumns = arrayOf("idPosition"),
            childColumns = arrayOf("idPosition"),
            onDelete = ForeignKey.CASCADE
        )
    )
)

data class Medecin(
    val idUser: Int?,
    val idPosition: Int?,
    val nom:String?,
    val prenom:String?,
    val idSpecialite:Int?,
    val anneesExp:Int?,
    val photo:String?
)
@PrimaryKey(autoGenerate = true)
var idMedecin: Int? = null
