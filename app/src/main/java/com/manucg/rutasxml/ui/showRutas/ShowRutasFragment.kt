package com.manucg.rutasxml.ui.showRutas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.manucg.rutasxml.ServiceViewModel
import com.manucg.rutasxml.databinding.FragmentShowRutasBinding
import com.manucg.rutasxml.model.dao.DaoSimpleXML
import com.manucg.rutasxml.model.entities.Ruta

class ShowRutasFragment : Fragment() {

    private var binding: FragmentShowRutasBinding? = null
    private val svm: ServiceViewModel by activityViewModels()
    private var mColumnCount = 1
    private lateinit var listaRutas: MutableList<Ruta>
    private lateinit var recyclerView: RecyclerView
    private lateinit var miAdapter: RutasRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentShowRutasBinding.inflate(inflater, container, false)
        val root: View = binding!!.root

        if (root is RecyclerView) {
            val context = root.context
            recyclerView = root
            recyclerView.layoutManager = if (mColumnCount <= 1) {
                LinearLayoutManager(context)
            } else {
                GridLayoutManager(context, mColumnCount)
            }
            var daoSimpleXML = DaoSimpleXML(this.requireContext(), svm)
            //daoSimpleXML.vaciarArchivoInterno()
            daoSimpleXML.ProcesarArchivoXMLInterno()
            //daoSimpleXML.procesarArchivoAssetsXML()
            listaRutas=svm.rutas
            miAdapter = RutasRecyclerViewAdapter(listaRutas)
            svm.miAdapter=miAdapter
            recyclerView.adapter = miAdapter
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}