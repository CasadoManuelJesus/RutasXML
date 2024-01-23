package com.manucg.rutasxml.ui.showRutas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.manucg.rutasxml.R
import com.manucg.rutasxml.model.entities.Ruta

class RutasRecyclerViewAdapter(private val rutas: MutableList<Ruta>) :
    RecyclerView.Adapter<RutasRecyclerViewAdapter.ViewHolder>() {
    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val textRuta: TextView = mView.findViewById(R.id.textRuta)

        // Paso 6: Override del método toString para proporcionar una representación en cadena
        override fun toString(): String {
            return super.toString() + " '" + textRuta.text + "'"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ruta, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ruta = rutas[position]
        holder.textRuta.text = ruta.toString()
    }

    override fun getItemCount(): Int {
        return rutas.size
    }
}