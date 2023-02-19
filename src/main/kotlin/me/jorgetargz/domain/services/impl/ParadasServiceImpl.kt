package me.jorgetargz.domain.services.impl

import me.jorgetargz.data.ParadasRepository
import me.jorgetargz.domain.modelo.Parada
import me.jorgetargz.domain.services.ParadasService

class ParadasServiceImpl(
    private val paradasRepository: ParadasRepository
) : ParadasService {

    override fun getParadasByLineaId(lineaId: Int) = paradasRepository.getParadasByLineaId(lineaId)

    override fun createParada(parada: Parada) = paradasRepository.createParada(parada)

    override fun updateParada(parada: Parada) = paradasRepository.updateParada(parada)

    override fun deleteParada(parada: Parada) = paradasRepository.deleteParada(parada)
}