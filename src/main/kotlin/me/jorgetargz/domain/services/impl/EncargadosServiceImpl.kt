package me.jorgetargz.domain.services.impl

import me.jorgetargz.data.EncargadosRepository
import me.jorgetargz.data.impl.EncargadosRepositoryImpl
import me.jorgetargz.domain.modelo.Encargado
import me.jorgetargz.domain.services.EncargadosService

class EncargadosServiceImpl (
    private val encargadosRepository: EncargadosRepository = EncargadosRepositoryImpl()
) : EncargadosService {
    override fun getEncargadoByParadaId(paradaId: Int) = encargadosRepository.getEncargadoByParadaId(paradaId)

    override fun createEncargado(encargado: Encargado) = encargadosRepository.createEncargado(encargado)

    override fun updateEncargado(encargado: Encargado) = encargadosRepository.updateEncargado(encargado)

    override fun deleteEncargado(encargado: Encargado) = encargadosRepository.deleteEncargado(encargado)
}
