package me.jorgetargz.ui.panel

import me.jorgetargz.domain.modelo.Linea
import me.jorgetargz.domain.modelo.Parada

sealed class PanelEvents {
    object GetPanelData : PanelEvents()
    class CreateLinea(val linea: Linea) : PanelEvents()
    class UpdateLinea(val linea: Linea) : PanelEvents()
    class DeleteLinea(val linea: Linea) : PanelEvents()
    class FilterParadas(val linea: Linea) : PanelEvents()
    class FilterEncargado(val parada: Parada) : PanelEvents()
    object ClearErrors : PanelEvents()
}