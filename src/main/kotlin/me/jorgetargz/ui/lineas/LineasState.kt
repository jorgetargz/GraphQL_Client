package me.jorgetargz.ui.lineas

import me.jorgetargz.domain.modelo.Linea

data class LineasState(val lineas: List<Linea> = emptyList(), val error: String? = null, val loading: Boolean = false)