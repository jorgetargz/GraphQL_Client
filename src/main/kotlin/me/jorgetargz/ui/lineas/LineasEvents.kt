package me.jorgetargz.ui.lineas

import me.jorgetargz.domain.modelo.Linea

sealed class LineasEvents {
    object GetLineas : LineasEvents()
    class CreateLinea(val linea: Linea) : LineasEvents()
    class UpdateLinea(val linea: Linea) : LineasEvents()
    class DeleteLinea(val linea: Linea) : LineasEvents()
    object ClearErrors : LineasEvents()
}