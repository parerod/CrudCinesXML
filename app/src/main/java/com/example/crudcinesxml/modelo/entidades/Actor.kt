package com.example.crudcinesxml.modelo.entidades

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "actores")
data class Actores(
    @field:ElementList(inline = true, entry = "actor", required = false)
    var actores: MutableList<Actor> = mutableListOf()
) {

}

@Root(name = "actor")
data class Actor(
    @field:Attribute(name = "nombre")
    var nombre: String = "",

    @field:Attribute(name = "papel")
    var papel: String = ""
) {
}