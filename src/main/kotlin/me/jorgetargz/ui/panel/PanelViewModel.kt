package me.jorgetargz.ui.panel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.jorgetargz.domain.modelo.Linea
import me.jorgetargz.domain.modelo.Parada
import me.jorgetargz.domain.services.EncargadosService
import me.jorgetargz.domain.services.LineaService
import me.jorgetargz.domain.services.ParadasService
import me.jorgetargz.utils.NetworkResult

class PanelViewModel(
    private val lineaService: LineaService = LineaService(),
    private val paradaService: ParadasService = ParadasService(),
    private val encargadoService: EncargadosService = EncargadosService(),
) {
    private val _uiState = MutableStateFlow(PanelState())
    val uiState: StateFlow<PanelState> get() = _uiState

    private fun getAllLineas() {
        CoroutineScope(Dispatchers.IO).launch {
            lineaService.getAllLineas().collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        val lineas = result.data!!
                        val paradas = lineas.flatMap { it.paradas }
                        val encargados = paradas.map { it.encargado }
                        _uiState.update { it.copy(
                            lineas = lineas,
                            paradas = paradas,
                            encargados = encargados,
                            loading = false) }
                    }

                    is NetworkResult.Error -> {
                        _uiState.update { it.copy(
                            error = result.message,
                            loading = false) }
                    }

                    is NetworkResult.Loading -> {
                        _uiState.update { it.copy(
                            loading = true) }
                    }
                }
            }
        }
    }

    private fun loadParadasByLinea(linea: Linea) {
        CoroutineScope(Dispatchers.IO).launch {
            paradaService.getParadasByLineaId(linea.id).collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _uiState.update { it.copy(
                            paradas = result.data!!,
                            encargados = result.data!!.map { it.encargado },
                            loading = false) }
                    }

                    is NetworkResult.Error -> {
                        _uiState.update { it.copy(
                            error = result.message,
                            loading = false) }
                    }

                    is NetworkResult.Loading -> {
                        _uiState.update { it.copy(
                            loading = true) }
                    }
                }
            }
        }
    }

    private fun loadEncargadoByParada(parada: Parada) {
        CoroutineScope(Dispatchers.IO).launch {
            encargadoService.getEncargadoByParadaId(parada.id).collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _uiState.update { it.copy(
                            encargados = listOf(result.data!!),
                            loading = false) }
                    }

                    is NetworkResult.Error -> {
                        _uiState.update { it.copy(
                            error = result.message,
                            loading = false) }
                    }

                    is NetworkResult.Loading -> {
                        _uiState.update { it.copy(
                            loading = true) }
                    }
                }
            }
        }
    }

    private fun createLinea(linea: Linea) {
        CoroutineScope(Dispatchers.IO).launch {
            lineaService.createLinea(linea).collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _uiState.update { it.copy(
                            lineas = _uiState.value.lineas.plus(result.data!!),
                            loading = false) }
                    }

                    is NetworkResult.Error -> {
                        _uiState.update { it.copy(
                            error = result.message,
                            loading = false) }
                    }

                    is NetworkResult.Loading -> {
                        _uiState.update { it.copy(
                            loading = true) }
                    }
                }
            }
        }
    }

    private fun updateLinea(linea: Linea) {
        CoroutineScope(Dispatchers.IO).launch {
            lineaService.updateLinea(linea).collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _uiState.update {
                            it.copy(
                                lineas = _uiState.value.lineas.map { linea1 ->
                                    if (linea1.id == linea.id) {
                                        linea1.copy(tipo = linea.tipo, numero = linea.numero)
                                    } else {
                                        linea1
                                    }
                                },
                                loading = false
                            )
                        }
                    }

                    is NetworkResult.Error -> {
                        _uiState.update { it.copy(
                            error = result.message,
                            loading = false) }
                    }

                    is NetworkResult.Loading -> {
                        _uiState.update { it.copy(
                            loading = true) }
                    }
                }
            }
        }
    }

    private fun deleteLinea(linea: Linea) {
        CoroutineScope(Dispatchers.IO).launch {
            lineaService.deleteLinea(linea).collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _uiState.update {
                            it.copy(
                                lineas = _uiState.value.lineas.filter { lineaL -> lineaL.id != linea.id },
                                loading = false
                            )
                        }
                    }

                    is NetworkResult.Error -> {
                        _uiState.update { it.copy(
                            error = result.message,
                            loading = false) }
                    }

                    is NetworkResult.Loading -> {
                        _uiState.update { it.copy(
                            loading = true) }
                    }
                }
            }
        }
    }

    private fun createParada(parada: Parada) {
        CoroutineScope(Dispatchers.IO).launch {
            paradaService.createParada(parada).collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _uiState.update { it.copy(
                            paradas = _uiState.value.paradas.plus(result.data!!),
                            loading = false) }
                    }

                    is NetworkResult.Error -> {
                        _uiState.update { it.copy(
                            error = result.message,
                            loading = false) }
                    }

                    is NetworkResult.Loading -> {
                        _uiState.update { it.copy(
                            loading = true) }
                    }
                }
            }
        }
    }

    private fun updateParada(parada: Parada) {
        CoroutineScope(Dispatchers.IO).launch {
            paradaService.updateParada(parada).collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _uiState.update {
                            it.copy(
                                paradas = _uiState.value.paradas.map { parada1 ->
                                    if (parada1.id == parada.id) {
                                        parada1.copy(nombre = parada.nombre, direccion = parada.direccion)
                                    } else {
                                        parada1
                                    }
                                },
                                loading = false
                            )
                        }
                    }

                    is NetworkResult.Error -> {
                        _uiState.update { it.copy(
                            error = result.message,
                            loading = false) }
                    }

                    is NetworkResult.Loading -> {
                        _uiState.update { it.copy(
                            loading = true) }
                    }
                }
            }
        }
    }

    private fun deleteParada(parada: Parada) {
        CoroutineScope(Dispatchers.IO).launch {
            paradaService.deleteParada(parada).collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _uiState.update {
                            it.copy(
                                paradas = _uiState.value.paradas.filter { paradaL -> paradaL.id != parada.id },
                                loading = false
                            )
                        }
                    }

                    is NetworkResult.Error -> {
                        _uiState.update { it.copy(
                            error = result.message,
                            loading = false) }
                    }

                    is NetworkResult.Loading -> {
                        _uiState.update { it.copy(
                            loading = true) }
                    }
                }
            }
        }
    }

    private fun clearErrors() {
        _uiState.update { it.copy(
            error = null) }
    }

    fun handleEvent(event: PanelEvents) {
        when (event) {
            is PanelEvents.GetPanelData -> getAllLineas()
            is PanelEvents.CreateLinea -> createLinea(event.linea)
            is PanelEvents.UpdateLinea -> updateLinea(event.linea)
            is PanelEvents.DeleteLinea -> deleteLinea(event.linea)
            is PanelEvents.CreateParada -> createParada(event.parada)
            is PanelEvents.UpdateParada -> updateParada(event.parada)
            is PanelEvents.DeleteParada -> deleteParada(event.parada)
            is PanelEvents.FilterParadas -> loadParadasByLinea(event.linea)
            is PanelEvents.FilterEncargado -> loadEncargadoByParada(event.parada)
            is PanelEvents.ClearErrors -> clearErrors()
        }
    }


}