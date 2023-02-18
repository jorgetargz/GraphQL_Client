package me.jorgetargz.domain.services

import me.jorgetargz.data.EncargadosRepository
import me.jorgetargz.domain.modelo.Encargado

class EncargadosService(
    private val encargadosRepository: EncargadosRepository = EncargadosRepository()
) {
    fun getEncargadoByParadaId(paradaId: Int) = encargadosRepository.getEncargadoByParadaId(paradaId)

    fun createEncargado(encargado: Encargado) = encargadosRepository.createEncargado(encargado)

    fun updateEncargado(encargado: Encargado) = encargadosRepository.updateEncargado(encargado)

    fun deleteEncargado(encargado: Encargado) = encargadosRepository.deleteEncargado(encargado)
}
