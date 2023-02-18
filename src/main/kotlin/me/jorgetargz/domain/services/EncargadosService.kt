package me.jorgetargz.domain.services

import me.jorgetargz.data.EncargadosRepository

class EncargadosService(
    private val encargadosRepository: EncargadosRepository = EncargadosRepository()
) {
    fun getEncargadoByParadaId(paradaId: Int) = encargadosRepository.getEncargadoByParadaId(paradaId)
}
