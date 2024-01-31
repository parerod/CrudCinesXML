package com.example.crudcinesxml.modelo.servicios

import com.example.crudcinesxml.modelo.entidades.*
import org.xml.sax.Attributes
import org.xml.sax.SAXException
import org.xml.sax.helpers.DefaultHandler

class CineHandler : DefaultHandler() {

    private val cadena = StringBuilder() //Concatena Cadenas


    var cine : Cine? = null
    var cines: MutableList<Cine> = mutableListOf()

    var salas : MutableList<Sala>? = null
    var sala : Sala? = null
    var actores : MutableList<Actor>? = null
    var actor : Actor? = null
    var pelicula : Pelicula? = null
    /*var alumnos : MutableList<Alumno> = mutableListOf()*/

    @Throws(SAXException::class)
    override fun startDocument() {
        cadena.clear()
        cines = mutableListOf()
        println("startDocument")
    }

    @Throws(SAXException::class)
    override fun startElement(uri: String, nombreLocal: String, nombre: String, attributes: Attributes) {
        cadena.setLength(0)
        if (nombre == "actor") {
            actor = Actor()

            actor?.nombre = attributes.getValue("nombre")
            actor?.papel = attributes.getValue("papel")
        }else if (nombre == "pelicula") {
            pelicula = Pelicula()

            pelicula?.anho = attributes.getValue("anho")
            pelicula?.director = attributes.getValue("director")
            pelicula?.nombre = attributes.getValue("nombre")


        } else if (nombre == "sala") {
            sala = Sala()

            sala?.aforo = attributes.getValue("aforo")
            sala?.numero = attributes.getValue("numero")
        } else if (nombre == "cine") {
            cine = Cine()

            cine?.localidad = attributes.getValue("localidad")
            cine?.nombre = attributes.getValue("nombre")
        }

        println("startElement: $nombreLocal $nombre")
    }

    @Throws(SAXException::class)
    override fun characters(ch: CharArray, start: Int, length: Int) {
        cadena.append(ch, start, length)
        println("dato final: $cadena")
    }

    @Throws(SAXException::class)
    override fun endElement(uri: String, nombreLocal: String, nombre: String) {
        when (nombre) {
            "actor" -> actor?.let { actores?.add(it) }
            "actores" -> pelicula?.actores = actores?.let { Actores(it) }!!
            "sinopsis" -> pelicula?.sinopsis = cadena.toString()
            "genero" -> pelicula?.genero = cadena.toString()
            "duracion" -> pelicula?.duracion = cadena.toString()
            "idioma" -> pelicula?.idioma = cadena.toString()
            "clasificacion" -> pelicula?.clasificacion = cadena.toString()
            "pelicula" -> sala?.pelicula = pelicula
            "sala" -> sala?.let { salas?.add(it) }
            "salas" -> cine?.salas = salas?.let { Salas(it) }
            "telefono" -> cine?.telefono = cadena.toString()
            "direccion" -> cine?.direccion = cadena.toString()
            "cine" -> cine?.let { cines.add(it) }
        }



        println("endElement: $nombreLocal $nombre")
    }

    @Throws(SAXException::class)
    override fun endDocument() {
        println("endDocument")
    }


}

