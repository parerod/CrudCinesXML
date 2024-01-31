package com.example.crudcinesxml.modelo.entidades

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root


@Root(name = "salas")
data class Salas(
    @field:ElementList(inline = true, entry = "sala")
    var salas: MutableList<Sala> = mutableListOf()
){

}

@Root(name = "sala")
data class Sala(
    @field:Attribute(name = "aforo")
    var aforo: String? = "",

    @field:Attribute(name = "numero")
    var numero: String? = "",

    @field:Element(name = "pelicula", required = false)
    var pelicula: Pelicula? = null
) {
}