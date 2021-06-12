package com.example.projet_tdm.Entities

import androidx.room.PrimaryKey

data class User(
    @PrimaryKey
    val num_tel: String?,
    val password: String?
)

@PrimaryKey(autoGenerate = true)
var userId: Int? = null

