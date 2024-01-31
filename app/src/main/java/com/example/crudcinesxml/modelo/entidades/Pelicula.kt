package com.example.crudcinesxml.modelo.entidades

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "pelicula")
data class Pelicula(
    @field:Attribute(name = "anho")
    var anho: String = "",

    @field:Attribute(name = "director")
    var director: String = "",

    @field:Attribute(name = "nombre")
    var nombre: String = "",

    @field:Element(name = "clasificacion")
    var clasificacion: String = "",

    @field:Element(name = "idioma")
    var idioma: String = "",

    @field:Element(name = "duracion")
    var duracion: String = "",

    @field:Element(name = "genero")
    var genero: String = "",

    @field:Element(name = "sinopsis")
    var sinopsis: String = "",

    @field:Element(name = "actores")
    var actores: Actores = Actores()

) {
}