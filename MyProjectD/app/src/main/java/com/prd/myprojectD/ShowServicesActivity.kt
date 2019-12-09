package com.prd.myprojectD

import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.prd.myprojectD.api.Connection
import com.prd.myprojectD.data.ServiceHistory
import com.prd.myprojectD.data.ServiceResponses

import kotlinx.android.synthetic.main.activity_show_services.*
import kotlinx.android.synthetic.main.content_home.*
import kotlinx.android.synthetic.main.content_show_services.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowServicesActivity : AppCompatActivity() {

    private val service = Connection().createConection()

    private var adapter: HistoryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_services)
        setSupportActionBar(toolbar)

        rvServicesHistory.layoutManager = LinearLayoutManager(this)

        val parentLayout = findViewById<View>(android.R.id.content)
        fillRecicle(parentLayout)
    }

    fun fillRecicle(view: View) {

        val id = intent.getStringExtra("CPF")
        service?.getHistory(id)
            ?.enqueue(object : Callback<List<ServiceHistory>> {
                override fun onResponse(
                    call: Call<List<ServiceHistory>>,
                    response: Response<List<ServiceHistory>>
                ) {
                    if (response.isSuccessful) {
                        if (response.body() != emptyList<List<ServiceResponses>>()) {
                            adapter = HistoryAdapter(response.body()!!, this@ShowServicesActivity)
                            rvServicesHistory.adapter = adapter
                            rvServicesHistory.visibility = View.VISIBLE
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

                override fun onFailure(call: Call<List<ServiceHistory>>, t: Throwable) {
                    Snackbar.make(
                        view,
                        "falhou",
                        Snackbar.LENGTH_LONG
                    )
                        .setAction("Action", null).show()

                }
            })

    }

}
