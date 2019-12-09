package com.prd.myprojectD

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.prd.myprojectD.api.Connection
import com.prd.myprojectD.data.ServiceResponses
import com.prd.myprojectD.data.askServiceParameters
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Home : AppCompatActivity(), ServicesAdapter.OnStoreListener {

    private var adapter: ServicesAdapter? = null

    private val service = Connection().createConection()


    private var context: Context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        context = this
        val parentLayout = findViewById<View>(android.R.id.content)


        rvServices.layoutManager = LinearLayoutManager(this)

        fillRecicle(parentLayout)
        val id = intent.getStringExtra("CPF")
        btHistorico.setOnClickListener {
            val intent = Intent(this@Home, ShowServicesActivity::class.java).apply {
                putExtra("CPF", id)
            }
            startActivity(intent)
        }

    }

    fun fillRecicle(view: View) {


        service?.getAllServices()
            ?.enqueue(object : Callback<List<ServiceResponses>> {
                override fun onResponse(
                    call: Call<List<ServiceResponses>>,
                    response: Response<List<ServiceResponses>>
                ) {
                    if (response.isSuccessful) {
                        if (response.body() != emptyList<List<ServiceResponses>>()) {
                            adapter = ServicesAdapter(response.body()!!, this@Home, this@Home)
                            rvServices.adapter = adapter
                            rvServices.visibility = View.VISIBLE
                        } else {
                            Snackbar.make(
                                view,
                                "SEM SERVIÇOS DISPONIVEIS",
                                Snackbar.LENGTH_LONG
                            )
                                .setAction("Action", null).show()
                        }
                    } else {
                        Snackbar.make(
                            view,
                            "SEM SERVIÇOS DISPONIVEIS",
                            Snackbar.LENGTH_LONG
                        )
                            .setAction("Action", null).show()
                    }
                }

                override fun onFailure(call: Call<List<ServiceResponses>>, t: Throwable) {
                    Snackbar.make(
                        view,
                        "falhou",
                        Snackbar.LENGTH_LONG
                    )
                        .setAction("Action", null).show()

                }
            })

    }

    fun callSucsses() {
        val parentLayout = findViewById<View>(android.R.id.content)
        Snackbar.make(
            parentLayout, "serviço comprado com sucesso",
            Snackbar.LENGTH_LONG
        )
            .setAction("Action", null).show()
    }

    override fun onStoreSelected(store: ServiceResponses) {

        val intent = intent

        val id = intent.getStringExtra("CPF")

        service?.askService(id, askServiceParameters(store.providerCpf, store.serviceName))
            ?.enqueue(object : Callback<Void> {
                override fun onResponse(
                    call: Call<Void>,
                    response: Response<Void>
                ) {
                    if (response.isSuccessful) {
                        callSucsses()

                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {

                    t
                }
            })

    }


}
