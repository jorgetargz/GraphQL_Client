package me.jorgetargz.data

import me.jorgetargz.domain.modelo.Encargado
import me.jorgetargz.localhost.GetEncargadoByParadaIdQuery

class EncargadosRepository : BaseRepository() {

    fun getEncargadoByParadaId(paradaId: Int) = executeGraphQLQuery(
        GetEncargadoByParadaIdQuery(paradaId)
    ) { data ->
        data.parada?.encargado?.let {
            Encargado(
                it.id,
                it.nombre,
                it.dni,
            )
        } ?: Encargado()
    }
}