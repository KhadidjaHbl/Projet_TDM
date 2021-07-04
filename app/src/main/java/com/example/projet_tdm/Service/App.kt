package com.example.projet_tdm.Service
import android.app.Application
import com.example.projet_tdm.RoomDao.RoomService

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        RoomService.context = applicationContext
    }
}