package com.example.projet_tdm.Service

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import androidx.work.impl.utils.futures.SettableFuture
import com.example.projet_tdm.Entities.Conseil
import com.example.projet_tdm.Retrofit.RetrofitService
import com.example.projet_tdm.RoomDao.RoomService
import com.google.common.util.concurrent.ListenableFuture
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("RestrictedApi")
class SyncService(val ctx: Context, val workParamters: WorkerParameters) :
    ListenableWorker(ctx, workParamters) {

    lateinit var future: SettableFuture<Result>

    override fun startWork(): ListenableFuture<Result> {
        future = SettableFuture.create()
        val conseils = RoomService.appDataBase.getConseilDao().getConseilsToSynchronize()
        addConseils(conseils)
        return future
    }

    fun addConseils(conseils: List<Conseil>) {
        val result = RetrofitService.endpoint.addConseils(conseils)
        result.enqueue(object : Callback<String> {

            override fun onFailure(call: Call<String>?, t: Throwable?) {
                future.set(Result.retry())
            }
            override fun onResponse(call: Call<String>?, response: Response<String>?) {
                if (response?.isSuccessful!!) {
                    for (item in conseils) {
                        item.isSynchronized = 1
                    }
                    RoomService.appDataBase.getConseilDao().updateConseil(conseils)
                    future.set(ListenableWorker.Result.success())

                } else {
                    future.set(Result.retry())
                }
            }
        })
    }

}
