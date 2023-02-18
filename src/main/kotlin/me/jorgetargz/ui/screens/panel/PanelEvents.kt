package me.jorgetargz.ui.screens.panel

import me.jorgetargz.domain.modelo.Encargado
import me.jorgetargz.domain.modelo.Linea
import me.jorgetargz.domain.modelo.Parada

sealed class PanelEvents {
    object GetPanelData : PanelEvents()
    class CreateLinea(val linea: Linea) : PanelEvents()
    class UpdateLinea(val linea: Linea) : PanelEvents()
    class DeleteLinea(val linea: Linea) : PanelEvents()
    class CreateParada(val parada: Parada) : PanelEvents()
    class UpdateParada(val parada: Parada) : PanelEvents()
    class DeleteParada(val parada: Parada) : PanelEvents()
    class CreateEncargado(val encargado: Encargado) : PanelEvents()
    class UpdateEncargado(val encargado: Encargado) : PanelEvents()
    class DeleteEncargado(val encargado: Encargado) : PanelEvents()
    class FilterParadas(val linea: Linea) : PanelEvents()
    class FilterEncargado(val parada: Parada) : PanelEvents()
    object ClearErrors : PanelEvents()
}