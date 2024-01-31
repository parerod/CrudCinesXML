package com.example.crudcinesxml.modelo.entidades

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "cines")
data class Cines(
    @field:ElementList(inline = true, entry = "cine")
    var cines: MutableList<Cine> = mutableListOf()
) {

}

@Root(name = "cine")
data class Cine(

    @field:Attribute(name = "localidad")
    var localidad: String = "",

    @field:Attribute(name = "nombre")
    var nombre: String = "",

    @field:Element(name = "direccion")
    var direccion: String = "",

    @field:Element(name = "telefono")
    var telefono: String = "",

    @field:Element(name = "salas", required = false)
    var salas: Salas? = null
) {
}