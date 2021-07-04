package com.example.projet_tdm.Entities

import androidx.room.ForeignKey
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(
    tableName = "conseil"
)
data class Conseil(
    var idUser: Int?,
    var contenue: String?,
    var idMedecin: Int?,
    var isSynchronized: Int = 0

) {
    @PrimaryKey(autoGenerate = true)
    var idConseil: Int? = null
}

