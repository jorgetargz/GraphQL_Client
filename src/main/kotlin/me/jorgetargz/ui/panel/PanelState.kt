package me.jorgetargz.ui.panel

import me.jorgetargz.domain.modelo.Encargado
import me.jorgetargz.domain.modelo.Linea
import me.jorgetargz.domain.modelo.Parada

data class PanelState(
    val lineas: List<Linea> = emptyList(),
    val paradas: List<Parada> = emptyList(),
    val encargados: List<Encargado> = emptyList(),
    val error: String? = null,
    val loading: Boolean = false
)