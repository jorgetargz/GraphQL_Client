package me.jorgetargz.domain.services

import me.jorgetargz.data.LineaRepository
import me.jorgetargz.domain.modelo.Linea

class LineaService(
    private val lineaRepository: LineaRepository = LineaRepository()
) {

    fun getAllLineas() = lineaRepository.getAllLineas()

    fun createLinea(linea: Linea) = lineaRepository.createLinea(linea)

    fun updateLinea(linea: Linea) = lineaRepository.updateLinea(linea)

    fun deleteLinea(linea: Linea) = lineaRepository.deleteLinea(linea)
}