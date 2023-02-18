package me.jorgetargz.domain.services

import me.jorgetargz.data.ParadasRepository
import me.jorgetargz.domain.modelo.Parada

class ParadasService(
    private val paradasRepository: ParadasRepository = ParadasRepository()
) {
    fun getParadasByLineaId(lineaId: Int) = paradasRepository.getParadasByLineaId(lineaId)

    fun createParada(parada: Parada) = paradasRepository.createParada(parada)

    fun updateParada(parada: Parada) = paradasRepository.updateParada(parada)

    fun deleteParada(parada: Parada) = paradasRepository.deleteParada(parada)
}