package com.example

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.projet_tdm.R
import com.example.projet_tdm.ViewModels.RdvViewModel
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import kotlinx.android.synthetic.main.fragment_detail_rdv.*


class DetailRdvFragment : Fragment() {


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val vm= ViewModelProvider(requireActivity()).get(RdvViewModel::class.java)
        textViewnomp.text=vm.nomp
        textViewprenompat.text=vm.prenomp
        textViewdaterdv3.text=vm.Date_Rdv
        textViewheurd3.text=vm.Heure_Debut
        textViewheurf3.text=vm.Heure_Fin

        textViewnommed3.text=vm.nommed
        textViewprenommed3.text=vm.prenommed
        textViewspecrd3.text=vm.nomSpecialite
        //*********************QR****************

        val content = "Patient: "+vm.nomp+"--"+vm.prenomp+" a rendez vous le "+vm.Date_Rdv+" a "+vm.Heure_Debut+" chez Dr "+vm.nomp+" "+vm.prenomp

        val writer = QRCodeWriter()
        val bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, 512, 512)
        val width = bitMatrix.width
        val height = bitMatrix.height
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        for (x in 0 until width) {
            for (y in 0 until height) {
                bitmap.setPixel(x, y, if (bitMatrix.get(x, y)) Color.BLACK else Color.WHITE)
            }
        }
        imageViewQr.setImageBitmap(bitmap)
//*******************ENDQR******************

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_rdv, container, false)
    }


}