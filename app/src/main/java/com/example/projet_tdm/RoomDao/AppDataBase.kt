package com.example.projet_tdm.RoomDao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.projet_tdm.Entities.Conseil

@Database(entities = arrayOf(Conseil::class), version = 2)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getConseilDao(): ConseilDao
}





