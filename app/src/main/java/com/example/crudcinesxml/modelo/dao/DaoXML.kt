package com.example.crudcinesxml.modelo.dao

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.crudcinesxml.modelo.entidades.Cine
import com.example.crudcinesxml.modelo.entidades.Cines
import com.example.crudcinesxml.modelo.servicios.CineHandler
import org.simpleframework.xml.core.Persister
import java.io.*
import javax.xml.parsers.SAXParserFactory

class DaoXML(private val context: Context) {

    fun procesarArchivoXMLSAX() : MutableList<Cine> {

        var cines : MutableList<Cine> = mutableListOf()

        try {
            val factory = SAXParserFactory.newInstance()
            val parser = factory.newSAXParser()
            val handler = CineHandler()

            val inputStream = context.assets.open("cines.xml")
            parser.parse(inputStream, handler)

            // Accede a la lista de profesores desde handler.profesores
            handler.cines.forEach {
                Log.d("SAX", "Cines: ${it.toString()}")

            }

            cines = handler.cines
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return cines
    }

    fun procesarArchivoAssetsXML(): MutableList<Cine> {
        var cines : MutableList<Cine> = mutableListOf()

        val serializer = Persister()
        var inputStream: InputStream? = null
        var reader: InputStreamReader? = null

        try {
            inputStream = context.assets.open("cines.xml")
            reader = InputStreamReader(inputStream)
            val cinesListType = serializer.read(Cines::class.java, reader, false)
            cines.addAll(cinesListType.cines)


        } catch (e: Exception) {
            // Manejo de errores
            e.printStackTrace()
        } finally {
            // Cerrar inputStream y reader
            try {
                reader?.close()
                inputStream?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        return cines

    }

    fun addCine(cine: Cine): MutableList<Cine> {
        var cines : MutableList<Cine> = procesarArchivoAssetsXML()
        try {
            val serializer = Persister()
            cines.add(cine)
            val profesList = Cines(cines)
            val outputStream = context.openFileOutput("cines.xml", AppCompatActivity.MODE_PRIVATE)
            serializer.write(profesList, outputStream)
            outputStream.close() // Asegúrate de cerrar el outputStream después de escribir
        } catch (e: Exception) {
            e.printStackTrace() // Manejo de errores adecuado
        }
        return cines
    }
    fun copiarArchivoDesdeAssets() {
        val nombreArchivo = "cines.xml"
        val archivoEnAssets = context.assets.open(nombreArchivo)
        val archivoInterno = context.openFileOutput(nombreArchivo, AppCompatActivity.MODE_PRIVATE)

        archivoEnAssets.copyTo(archivoInterno)
        archivoEnAssets.close()
        archivoInterno.close()
    }

    fun procesarArchivoXMLInterno(): MutableList<Cine> {
        var cines : MutableList<Cine> = mutableListOf()
        val nombreArchivo = "cines.xml"
        val serializer = Persister()

        try {
            // Abrir el archivo para lectura
            val file = File(context.filesDir, nombreArchivo)
            val inputStream = FileInputStream(file)
            val cinesList = serializer.read(Cines::class.java, inputStream)
            cines.addAll(cinesList.cines)
            inputStream.close()
        } catch (e: Exception) {

            e.printStackTrace()
        }

        return cines
    }

}