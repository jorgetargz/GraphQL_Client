package me.jorgetargz.ui.lineas

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.jorgetargz.domain.modelo.Linea
import me.jorgetargz.domain.services.LineaService
import me.jorgetargz.utils.NetworkResult

class LineasViewModel {

    private val lineaService = LineaService()

    private val _uiState = MutableStateFlow(LineasState())
    val uiState: StateFlow<LineasState> get() = _uiState

    private fun getAllLineas() {
        CoroutineScope(Dispatchers.IO).launch {
            lineaService.getAllLineas().collect() { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _uiState.update { it.copy(
                            lineas = result.data!!,
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
            lineaService.createLinea(linea.tipo, linea.numero).collect() { result ->
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
            lineaService.updateLinea(linea.id, linea.tipo, linea.numero).collect() { result ->
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

    private fun deleteLinea(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            lineaService.deleteLinea(id).collect() { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _uiState.update {
                            it.copy(
                                lineas = _uiState.value.lineas.filter { linea -> linea.id != id },
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

    fun handleEvent(event: LineasEvents) {
        when (event) {
            is LineasEvents.GetLineas -> getAllLineas()
            is LineasEvents.CreateLinea -> createLinea(event.linea)
            is LineasEvents.UpdateLinea -> updateLinea(event.linea)
            is LineasEvents.DeleteLinea -> deleteLinea(event.linea.id)
            is LineasEvents.ClearErrors -> clearErrors()
        }
    }


}