package com.example.projet_tdm.Activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.example.projet_tdm.*
import com.example.projet_tdm.Entities.User
import com.example.projet_tdm.Retrofit.RetrofitService
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val pref = getSharedPreferences("data", Context.MODE_PRIVATE)
        val Medconn = pref.getBoolean("medConnected", false)
        val Patconn = pref.getBoolean("patConnected", false)
        val idUser = pref.getInt("idUser", 0)

        val patientHome = Intent(this, PatientHome::class.java)
        val medecinHome = Intent(this, MedecinHome::class.java)


        if (Medconn) {
            startActivity(medecinHome)
            finish()
        }
        if(Patconn) {
            Toast.makeText(getApplicationContext(), "trueeee", Toast.LENGTH_LONG).show();
            startActivity(patientHome)
            finish()
        }

        //click sur le bouton se connecter
        connection.setOnClickListener {
            val num_tel = numT.text.toString()
            val password = pwd.text.toString()
            if (validateInputs(num_tel, password)) {
                val user = User(num_tel, password)
                val call = RetrofitService.endpoint.login(user)
                call.enqueue(object : Callback<JsonObject> {

                    override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                        if (response.isSuccessful) {
                            val jsonObject = JSONObject(Gson().toJson(response.body()))
                            val success = jsonObject.getString("success")
                            if (success.equals("1")) {
                                val id=jsonObject.getInt("idUser")
                                Toast.makeText(getApplicationContext(), "Useeeeeeeer"+id, Toast.LENGTH_LONG).show()

                                if(jsonObject.getString("type").equals("medecin")) {
                                    pref.edit {
                                        putBoolean("medConnected", true)
                                        putInt("idUser",id)
                                    }
                                    startActivity(medecinHome)
                                    finish()
                                }
                                else if(jsonObject.getString("type").equals("patient")){
                                    pref.edit {
                                        putBoolean("patConnected", true)
                                        putInt("idUser",id)
                                    }
                                    startActivity(patientHome)
                                    finish()
                                }

                            } else if (success.equals("0")) {
                                Toast.makeText(getApplicationContext(), "invalid login", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "not successful", Toast.LENGTH_LONG).show();
                        }
                    }

                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                        Toast.makeText(getApplicationContext(), "Response Error", Toast.LENGTH_LONG).show();
                    }
                })
            }
        }
    }

    //Vérifier que les données introduites par l'utilisateur ne sont pas vides
    private fun validateInputs(num: String, password: String): Boolean {
        if (num.isEmpty()) {
            numT.setError("phone number cannot be empty");
            numT.requestFocus();
        }
        if (password.isEmpty()) {
            pwd.setError("Password cannot be empty");
            pwd.requestFocus();
            return false;
        }
        return true;
    }
}
