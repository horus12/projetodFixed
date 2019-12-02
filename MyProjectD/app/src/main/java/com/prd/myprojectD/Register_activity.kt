package com.prd.myprojectD

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.prd.myprojectD.api.Connection
import com.prd.myprojectD.data.RegisterParameters

import kotlinx.android.synthetic.main.activity_register_activity.*
import kotlinx.android.synthetic.main.content_register_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Register_activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_activity)
        setSupportActionBar(toolbar)

        btRegistrarService.setOnClickListener { view ->
            register(
                RegisterParameters(
                    userName = editNome.text.toString(),
                    senha = editPassword.text.toString(),
                    cpf = editCpf.text.toString(),
                    rg = editRG.text.toString(),
                    contact = editContato.text.toString()
                ),view
            )
        }
    }


    private fun register(parameters: RegisterParameters,view: View) {

        val service = Connection().createConection()
        service?.register(parameters)
            ?.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.code()==200) {
                        Toast.makeText(this@Register_activity, "registrado com sucesso", Toast.LENGTH_SHORT).show()
                        finish()

                    } else

                    if(response.code()==409)
                    Snackbar.make(view, "Ja existe um usuario cadastrado com este cpf", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()

                    if(response.code()==401)
                    Snackbar.make(view, "CPF invalido", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()

                    if(response.code()==400)
                    Snackbar.make(view, "Preencha todos os dados", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()

                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Snackbar.make(view, "Erro inesperado", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()

                }
            })


    }
}
