package com.example.projet_tdm.Entities

import androidx.room.PrimaryKey

data class Specialite(
    val nomSpecialite:String?
)

@PrimaryKey(autoGenerate = true)
var idSpecialite: Int? = null
