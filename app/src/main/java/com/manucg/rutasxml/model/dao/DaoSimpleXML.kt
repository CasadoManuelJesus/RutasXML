package com.manucg.rutasxml.model.dao

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.manucg.rutasxml.ServiceViewModel
import com.manucg.rutasxml.model.entities.Ruta
import com.manucg.rutasxml.model.entities.Rutas
import org.simpleframework.xml.core.Persister
import java.io.*

class DaoSimpleXML(private val context: Context, private val svm: ServiceViewModel) {

    fun procesarArchivoAssetsXML() {
        // Crea un objeto serializador Persister
        val serializer = Persister()

        // Inicializa InputStream y Reader como nulos para garantizar su cierre en el bloque finally
        var inputStream: InputStream? = null
        var reader: InputStreamReader? = null

        try {
            // Abre un flujo de entrada para leer el archivo XML desde la carpeta 'assets'
            inputStream = context.assets.open("rutas.xml")

            // Crea un objeto InputStreamReader para leer caracteres del flujo de entrada
            reader = InputStreamReader(inputStream)

            // Utiliza Persister para deserializar el contenido del archivo XML en un objeto Rutas
            val rutasListType = serializer.read(Rutas::class.java, reader, false)

            // Agrega todas las rutas del objeto deserializado a la lista 'rutas'
            svm.rutas.addAll(rutasListType.ruta)
        } catch (e: Exception) {
            // Manejo de errores en caso de que ocurra una excepción durante la lectura/deserialización
            e.printStackTrace()
        } finally {
            // Cierra inputStream y reader en el bloque finally para garantizar su cierre
            try {
                reader?.close()
                inputStream?.close()
            } catch (e: IOException) {
                // Manejo de errores en caso de que ocurra una excepción al cerrar los flujos
                e.printStackTrace()
            }
        }
    }

    fun addRuta(ruta: Ruta) {
        try {
            val serializer = Persister()
            var misrutas = mutableListOf<Ruta>()
            misrutas.addAll(svm.rutas)
            misrutas.add(ruta)
            val profesoresList = Rutas(misrutas)

            val outputStream = context.openFileOutput("rutas.xml", AppCompatActivity.MODE_PRIVATE)
            serializer.write(profesoresList, outputStream)
            outputStream.close() // Asegúrate de cerrar el outputStream después de escribir
        } catch (e: Exception) {
            e.printStackTrace() // Manejo de errores adecuado
        }
    }

    fun copiarArchivoDesdeAssets() {
        val nombreArchivo = "rutas.xml"
        val archivoEnAssets = context.assets.open(nombreArchivo)
        val archivoInterno = context.openFileOutput(nombreArchivo, AppCompatActivity.MODE_PRIVATE)

        archivoEnAssets.copyTo(archivoInterno)
        archivoEnAssets.close()
        archivoInterno.close()
    }

    fun vaciarArchivoInterno() {
        val nombreArchivo = "rutas.xml"

        // Abre el archivo interno para escritura (esto eliminará el contenido existente)
        val archivoInternoEscritura =
            context.openFileOutput(nombreArchivo, AppCompatActivity.MODE_PRIVATE)
        archivoInternoEscritura.write("".toByteArray())

        // Cierra el archivo
        archivoInternoEscritura.close()
    }

    fun ProcesarArchivoXMLInterno() {
        val nombreArchivo = "rutas.xml"
        val serializer = Persister()

        try {
            // Abrir el archivo para lectura
            val file = File(context.filesDir, nombreArchivo)
            val inputStream = FileInputStream(file)
            val rutasList = serializer.read(Rutas::class.java, inputStream)
            //rutas.addAll(rutasList.ruta)
            svm.rutas = rutasList.ruta as MutableList<Ruta>
            inputStream.close()
        } catch (e: Exception) {

            e.printStackTrace()
        }
    }

}