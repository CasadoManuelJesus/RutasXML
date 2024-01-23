package com.manucg.rutasxml.model.entities

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "rutas")
data class Rutas(
    @field:ElementList(inline = true, entry = "ruta")
    var ruta: List<Ruta> = mutableListOf()
)

@Root(name = "ruta")
data class Ruta(
    @field:Element(name = "descripcion")
    var descripcion: String = "",

    @field:Attribute(name = "coordX", required = true)
    var coordX: Double = 0.0,

    @field:Attribute(name = "coordY", required = true)
    var coordY: Double = 0.0,

    @field:Attribute(name = "nombre", required = true)
    var nombre: String = ""
){
    override fun toString(): String {
        return "$nombre (x: $coordX ; y: $coordY): $descripcion"
    }
}
