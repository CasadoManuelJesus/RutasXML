package com.manucg.rutasxml.model.dao

import android.content.Context
import android.util.Log
import com.manucg.rutasxml.RutaHandler
import com.manucg.rutasxml.ServiceViewModel
import com.manucg.rutasxml.model.entities.Ruta
import javax.xml.parsers.SAXParserFactory

class DaoSAX(private val context: Context, private val svm : ServiceViewModel) {

    fun procesarArchivoAssetsXMLSAX() {
        try {
            // Crea una instancia de la fábrica de SAXParser
            val factory = SAXParserFactory.newInstance()

            // Crea un nuevo objeto SAXParser a partir de la fábrica
            val parser = factory.newSAXParser()

            // Crea un objeto de tu clase personalizada que extiende DefaultHandler (RutaHandler)
            val handler = RutaHandler()

            // Abre un flujo de entrada para leer el archivo XML desde la carpeta 'assets'
            val inputStream = context.assets.open("rutas.xml")

            // Parsea el archivo XML utilizando el handler personalizado
            parser.parse(inputStream, handler)

            // Accede a la lista de rutas desde el handler (RutaHandler)
            handler.rutas.forEach {
                // Imprime información sobre cada ruta en el archivo XML
                Log.d("SAX", "Ruta: ${it.nombre}")
                svm.rutas.add(it)
            }
        } catch (e: Exception) {
            // Maneja las excepciones imprimiendo la traza en la consola
            e.printStackTrace()
        }
    }


}