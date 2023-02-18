package me.jorgetargz.data

import kotlinx.coroutines.flow.Flow
import me.jorgetargz.domain.modelo.Encargado
import me.jorgetargz.domain.modelo.Linea
import me.jorgetargz.domain.modelo.Parada
import me.jorgetargz.localhost.*
import me.jorgetargz.utils.NetworkResult

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

    fun createParada(parada: Parada): Flow<NetworkResult<Parada>> = executeGraphQLMutation(
        CreateParadaMutation(parada.nombre, parada.direccion, parada.encargado.id, parada.linea.id)
    ) { data ->
        Parada(
            data.createParada?.id ?: 0,
            data.createParada?.nombre ?: "",
            data.createParada?.direccion ?: "",
            data.createParada?.encargado?.let { encargado ->
                Encargado(
                    encargado.id,
                    encargado.nombre,
                    encargado.dni,
                )
            } ?: Encargado(),
            data.createParada?.linea?.let { linea ->
                Linea(
                    linea.id,
                    linea.numero,
                    linea.tipo,
                    emptyList()
                )
            } ?: Linea()
        )
    }

    fun updateParada(parada: Parada): Flow<NetworkResult<Parada>> = executeGraphQLMutation(
        UpdateParadaMutation(parada.id, parada.nombre, parada.direccion, parada.encargado.id, parada.linea.id)
    ) { data ->
        Parada(
            data.updateParada?.id ?: 0,
            data.updateParada?.nombre ?: "",
            data.updateParada?.direccion ?: "",
            data.updateParada?.encargado?.let { encargado ->
                Encargado(
                    encargado.id,
                    encargado.nombre,
                    encargado.dni,
                )
            } ?: Encargado(),
            data.updateParada?.linea?.let { linea ->
                Linea(
                    linea.id,
                    linea.numero,
                    linea.tipo,
                    emptyList()
                )
            } ?: Linea()
        )
    }

    fun deleteParada(parada: Parada): Flow<NetworkResult<Parada>> = executeGraphQLMutation(
        DeleteParadaMutation(parada.id)
    ) { data ->
        Parada(
            data.deleteParada?.id ?: 0
        )
    }

}