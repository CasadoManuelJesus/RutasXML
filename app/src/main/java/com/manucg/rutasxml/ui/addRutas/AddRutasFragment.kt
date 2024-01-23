package com.manucg.rutasxml.ui.addRutas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.manucg.rutasxml.ServiceViewModel
import com.manucg.rutasxml.databinding.FragmentAddRutasBinding
import com.manucg.rutasxml.model.dao.DaoSimpleXML
import com.manucg.rutasxml.model.entities.Ruta

class AddRutasFragment : Fragment() {

    private var binding: FragmentAddRutasBinding? = null
    private val svm : ServiceViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddRutasBinding.inflate(inflater, container, false)
        val root: View = binding!!.root
        var daoSimpleXML = DaoSimpleXML(requireContext(), svm)


        binding!!.btnAdd.setOnClickListener {
            var nombre = binding!!.textNombre.text.toString()
            var coordX = binding!!.textCoordX.text.toString().toDouble()
            var coordY = binding!!.textCoordY.text.toString().toDouble()
            var descripcion = binding!!.textDescripcion.text.toString()

            var ruta = Ruta(descripcion,coordX, coordY,nombre )
            daoSimpleXML.addRuta(ruta)

        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}