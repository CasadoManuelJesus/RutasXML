package com.manucg.rutasxml

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manucg.rutasxml.model.entities.Ruta
import com.manucg.rutasxml.ui.showRutas.RutasRecyclerViewAdapter

class ServiceViewModel : ViewModel() {
    var rutas : MutableList<Ruta> = mutableListOf()
    var miAdapter: RutasRecyclerViewAdapter = RutasRecyclerViewAdapter(rutas)

}