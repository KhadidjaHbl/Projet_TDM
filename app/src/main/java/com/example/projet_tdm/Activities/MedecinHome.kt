package com.example.projet_tdm.Activities

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.edit
import com.example.projet_tdm.R
import com.google.zxing.Result
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_medecin_home.*
import kotlinx.android.synthetic.main.activity_patient.toolbar
import me.dm7.barcodescanner.zxing.ZXingScannerView

class MedecinHome : AppCompatActivity(), ZXingScannerView.ResultHandler {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medecin_home)

        val pref = getSharedPreferences("data", Context.MODE_PRIVATE)

        toolbar.setOnMenuItemClickListener(object : MenuItem.OnMenuItemClickListener,
            Toolbar.OnMenuItemClickListener {
            override fun onMenuItemClick(item: MenuItem): Boolean {
                if (item.getItemId() == (R.id.exit)) {
                    pref.edit {
                        putBoolean("medConnected", false)
                    }
                    val loginIntent = Intent(this@MedecinHome, LoginActivity::class.java)
                    startActivity(loginIntent)
                    finish()
                }
                return false
            }
        })


        Dexter.withActivity(this)
            .withPermission(Manifest.permission.CAMERA)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                    zxscan.setResultHandler(this@MedecinHome)
                    zxscan.startCamera()
                }

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest?,
                    token: PermissionToken?
                ) {
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                    Toast.makeText(
                        this@MedecinHome,
                        "Vous devez accepter la permission",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }).check()

    }

    override fun handleResult(rawResult: Result?) {
        result.text=rawResult!!.text.toString()
    }


}

