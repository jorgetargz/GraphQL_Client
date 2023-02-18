package me.jorgetargz.domain.services

import me.jorgetargz.data.ParadasRepository

class ParadasService(
    private val paradasRepository: ParadasRepository = ParadasRepository()
) {
    fun getParadasByLineaId(lineaId: Int) = paradasRepository.getParadasByLineaId(lineaId)
}