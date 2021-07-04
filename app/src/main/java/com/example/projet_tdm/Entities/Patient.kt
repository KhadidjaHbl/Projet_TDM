package com.example.projet_tdm.Entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "patients", foreignKeys = arrayOf(
        ForeignKey(
            entity =
            User::class, parentColumns = arrayOf("idUser"),
            childColumns = arrayOf("idUser"),
            onDelete = ForeignKey.CASCADE
        )
    )
)

data class Patient(
    val idUser: Int,
    val nom: String?,
    val prenom: String?
)

@PrimaryKey(autoGenerate = true)
var idPatient: Int? = null

