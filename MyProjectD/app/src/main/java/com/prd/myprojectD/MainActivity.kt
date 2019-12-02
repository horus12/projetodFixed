package com.prd.myprojectD

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.prd.myprojectD.api.Connection
import com.prd.myprojectD.data.Login
import com.prd.myprojectD.data.LoginParameters

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->


            callLogin(editText2.text.toString(), editText.text.toString(), view)
        }

        btRegister.setOnClickListener { view ->

            val intent = Intent(this@MainActivity, Register_activity::class.java).apply {
                putExtra(EXTRA_MESSAGE, "deu certo")
            }
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun callLogin(username: String, password: String, view: View) {


        val service = Connection().createConection()

        service?.login(LoginParameters(username, password))
            ?.enqueue(object : Callback<Login> {
                override fun onResponse(call: Call<Login>, response: Response<Login>) {
                    if (response.isSuccessful) {
                        val intent = Intent(this@MainActivity, Home::class.java).apply {
                            putExtra(EXTRA_MESSAGE, "deu certo")
                        }
                        startActivity(intent)
                    } else
                        Snackbar.make(view, "Login invalido", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()

                }

                override fun onFailure(call: Call<Login>, t: Throwable) {
                    Snackbar.make(view, "Erro inexperado", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            })

    }


}
