package com.example.projet_tdm.Entities


import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

data class Position(
    val longitude: Float?,
    val latitude: Float?
)

@PrimaryKey(autoGenerate = true)
var idPosition: Int? = null

