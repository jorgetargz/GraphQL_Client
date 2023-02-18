package me.jorgetargz.domain.services

import me.jorgetargz.data.LineaRepository

class LineaService {

    private val lineaRepository = LineaRepository()

    suspend fun getAllLineas() = lineaRepository.getAllLineas()

    suspend fun getLineaById(id: Int) = lineaRepository.getLinea(id)

    suspend fun createLinea(tipo: String, numero: Int) = lineaRepository.createLinea(tipo, numero)

    suspend fun updateLinea(id: Int, tipo: String, numero: Int) = lineaRepository.updateLinea(id, tipo, numero)

    suspend fun deleteLinea(id: Int) = lineaRepository.deleteLinea(id)
}