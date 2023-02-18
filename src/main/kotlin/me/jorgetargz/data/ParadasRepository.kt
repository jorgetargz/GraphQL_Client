package me.jorgetargz.data

import me.jorgetargz.domain.modelo.Encargado
import me.jorgetargz.domain.modelo.Linea
import me.jorgetargz.domain.modelo.Parada
import me.jorgetargz.localhost.GetParadasByLineaIdQuery

class ParadasRepository : BaseRepository() {

    fun getParadasByLineaId(lineaId: Int) = executeGraphQLQuery(
        GetParadasByLineaIdQuery(lineaId)
    ) { data ->
        data.linea?.paradas?.map {
            Parada(
                it?.id ?: 0,
                it?.nombre ?: "",
                it?.direccion ?: "",
                it?.encargado?.let { encargado ->
                    Encargado(
                        encargado.id,
                        encargado.nombre,
                        encargado.dni,
                    )
                } ?: Encargado(),
                it?.linea?.let { linea ->
                    Linea(
                        linea.id,
                        linea.numero,
                        linea.tipo,
                        emptyList()
                    )
                } ?: Linea()
            )
        } ?: emptyList()
    }
}