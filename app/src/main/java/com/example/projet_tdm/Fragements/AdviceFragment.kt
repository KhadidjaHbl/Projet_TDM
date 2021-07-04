package com.example.projet_tdm.Fragements

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.work.*
import com.example.projet_tdm.Entities.Conseil
import com.example.projet_tdm.R
import com.example.projet_tdm.Retrofit.RetrofitService
import com.example.projet_tdm.RoomDao.RoomService
import com.example.projet_tdm.Service.SyncService
import kotlinx.android.synthetic.main.fragment_advice.*


class AdviceFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_advice, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pref = this.requireActivity().getSharedPreferences("data", Context.MODE_PRIVATE)
        var idUser = pref.getInt("idUser", 0)
        Toast.makeText(context, "iddd"+idUser, Toast.LENGTH_SHORT).show()

        send.setOnClickListener() { View ->
            val cont: String = message.text.toString()
            val id = arguments?.getInt("idmedecin")
            val conseilContent = Conseil(idUser, cont, id, 0)
            RoomService.context=this.requireActivity()
            RoomService.appDataBase.getConseilDao().addConseil(conseilContent)
            scheduleSycn()
        }
    }

    private fun scheduleSycn() {
        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.UNMETERED).
            //    setRequiresBatteryNotLow(true).
        build()
        val req = OneTimeWorkRequest.Builder(SyncService::class.java).setConstraints(constraints)
            .addTag("id1").build()
        val workManager = WorkManager.getInstance(requireActivity())
        workManager.enqueueUniqueWork("work", ExistingWorkPolicy.REPLACE, req)
    }
}