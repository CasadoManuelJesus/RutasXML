package com.manucg.rutasxml

import android.util.Log
import com.manucg.rutasxml.model.entities.Ruta
import org.xml.sax.Attributes
import org.xml.sax.SAXException
import org.xml.sax.helpers.DefaultHandler
import kotlin.CharArray
import kotlin.Int
import kotlin.String
import kotlin.Throws

class RutaHandler : DefaultHandler() {
    private val cadena = StringBuilder()
    private var ruta: Ruta?= null
    var rutas: MutableList<Ruta> = mutableListOf()


    @Throws(SAXException::class)
    override fun startDocument() {
        cadena.clear()
        rutas = mutableListOf()
        Log.d("Handler","startDocument")
    }

    @Throws(SAXException::class)
    override fun startElement(
        uri: String,
        nombreLocal: String,
        nombreCualif: String,
        atrbts: Attributes
    ) {
        cadena.setLength(0)
        if (nombreCualif == "ruta") {
            ruta = Ruta()
            ruta!!.nombre=(atrbts.getValue("nombre"))
            ruta!!.coordX=(atrbts.getValue("coordX")).toDouble()
            ruta!!.coordY=(atrbts.getValue("coordY")).toDouble()
            rutas.add(ruta!!)

        }
        Log.d("Handler", "startElement: $nombreLocal $nombreCualif")
    }

    @Throws(SAXException::class)
    override fun characters(chars: CharArray?, inicio: Int, lon: Int) {
        cadena.append(chars, inicio, lon)
        Log.d("Handler","dato final: $cadena")
    }

    @Throws(SAXException::class)
    override fun endElement(uri: String?, nombreLocal: String, nombreCualif: String) {
        when (nombreCualif) {
            "descripcion" -> ruta?.descripcion = cadena.toString()
        }
        Log.d("Handler","endtElement: $nombreLocal $nombreCualif")
    }

    @Throws(SAXException::class)
    override fun endDocument() {
        Log.d("Handler","endDocument")
    }


}