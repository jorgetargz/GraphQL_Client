package me.jorgetargz.domain.services.impl

import me.jorgetargz.data.LineasRepository
import me.jorgetargz.data.impl.LineaRepositoryImpl
import me.jorgetargz.domain.modelo.Linea
import me.jorgetargz.domain.services.LineasService

class LineaServiceImpl(
    private val lineaRepository: LineasRepository = LineaRepositoryImpl()
) : LineasService {

    override fun getAllLineas() = lineaRepository.getAllLineas()

    override fun createLinea(linea: Linea) = lineaRepository.createLinea(linea)

    override fun updateLinea(linea: Linea) = lineaRepository.updateLinea(linea)

    override fun deleteLinea(linea: Linea) = lineaRepository.deleteLinea(linea)
}