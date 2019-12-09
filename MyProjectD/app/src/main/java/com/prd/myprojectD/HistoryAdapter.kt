package com.prd.myprojectD

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prd.myprojectD.data.ServiceHistory
import com.prd.myprojectD.data.ServiceResponses
import kotlinx.android.synthetic.main.recicle_card_aluguel.view.*
import kotlinx.android.synthetic.main.recicle_card_aluguel.view.tvServiceDescription
import kotlinx.android.synthetic.main.recicle_card_aluguel.view.tvServiceName
import kotlinx.android.synthetic.main.recicle_card_aluguel.view.tvValue
import kotlinx.android.synthetic.main.recicle_card_history.view.*
import java.text.NumberFormat
import java.util.*



class HistoryAdapter(
    private val services: List<ServiceHistory>,
    val context: Context
) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    private val numberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))


    override fun getItemCount(): Int {
        return services.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.recicle_card_history,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.description?.text = "Nome do serviço: " + services.get(position).serviceName
        holder.serviceName?.text = "Nome do prestador: " + services.get(position).providerName
        holder.category?.text = "Category: " + services.get(position).category
        holder.value?.text = "Valor: " + numberFormat.format(services.get(position).value)


    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val description = view.tvServiceDescription
        val serviceName = view.tvServiceName
        val value = view.tvValue
        val category = view.tvBatata



    }



}
