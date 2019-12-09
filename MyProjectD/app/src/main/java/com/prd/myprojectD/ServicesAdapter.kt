package com.prd.myprojectD

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prd.myprojectD.data.ServiceResponses
import kotlinx.android.synthetic.main.recicle_card_aluguel.view.*
import java.text.NumberFormat
import java.util.*

class ServicesAdapter(
    private val services: List<ServiceResponses>,
    val context: Context,
    val onStoreListener: OnStoreListener
) : RecyclerView.Adapter<ServicesAdapter.ViewHolder>() {

    private val numberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))


    override fun getItemCount(): Int {
        return services.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.recicle_card_aluguel,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.description?.text = "Descrição: " + services.get(position).serviceDescription
        holder.serviceName?.text = "Serviço: " + services.get(position).serviceName
        holder.value?.text = "Valor: " + numberFormat.format(services.get(position).value)
        holder.bind()

    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val description = view.tvServiceDescription
        val serviceName = view.tvServiceName
        val value = view.tvValue
        fun bind() {
            itemView.setOnClickListener {


                val builder = AlertDialog.Builder(context)

                builder.setTitle("Alugar")

                builder.setMessage("Você realmente deseja contratar esse serviço")

                builder.setPositiveButton("SIM") { dialog, which ->
                    onStoreListener.onStoreSelected(services[layoutPosition])

                }

                builder.setNegativeButton("NÃO") { dialog, which -> }
                builder.setNeutralButton("Cancel") { _, _ -> }
                val dialog: AlertDialog = builder.create()
                dialog.show()

            }


        }

    }


    interface OnStoreListener {
        fun onStoreSelected(store: ServiceResponses)
    }
}


