package me.jorgetargz.data

import kotlinx.coroutines.flow.Flow
import me.jorgetargz.domain.modelo.Encargado
import me.jorgetargz.domain.modelo.Linea
import me.jorgetargz.domain.modelo.Parada
import me.jorgetargz.localhost.CreateLineaMutation
import me.jorgetargz.localhost.DeleteLineaMutation
import me.jorgetargz.localhost.GetAllLineasQuery
import me.jorgetargz.localhost.UpdateLineaMutation
import me.jorgetargz.utils.NetworkResult

class LineaRepository : BaseRepository() {

    fun getAllLineas(): Flow<NetworkResult<List<Linea>>> = executeGraphQLQuery(
        GetAllLineasQuery()
    ) { data ->
        data.allLineas?.map {
            Linea(
                it?.id ?: 0,
                it?.numero ?: 0,
                it?.tipo ?: "",
                it?.paradas?.map { parada ->
                    Parada(
                        parada?.id ?: 0,
                        parada?.nombre ?: "",
                        parada?.direccion ?: "",
                        parada?.encargado?.let { encargado ->
                            Encargado(
                                encargado.id,
                                encargado.nombre,
                                encargado.dni,
                            )
                        } ?: Encargado(),
                        parada?.linea?.let { linea ->
                            Linea(
                                linea.id,
                                linea.numero,
                                linea.tipo,
                            )
                        } ?: Linea()
                    )
                } ?: emptyList()
            )
        } ?: emptyList()
    }

    fun createLinea(linea: Linea): Flow<NetworkResult<Linea>> = executeGraphQLMutation(
        CreateLineaMutation(linea.tipo, linea.numero)
    ) { data ->
        Linea(
            data.createLinea?.id ?: 0,
            data.createLinea?.numero ?: 0,
            data.createLinea?.tipo ?: ""
        )
    }

    fun updateLinea(linea: Linea): Flow<NetworkResult<Linea>> = executeGraphQLMutation(
        UpdateLineaMutation(linea.id, linea.tipo, linea.numero)
    ) { data ->
        Linea(
            data.updateLinea?.id ?: 0,
            data.updateLinea?.numero ?: 0,
            data.updateLinea?.tipo ?: ""
        )
    }

    fun deleteLinea(linea: Linea): Flow<NetworkResult<Linea>> = executeGraphQLMutation(
        DeleteLineaMutation(linea.id)
    ) { data -> Linea(data.deleteLinea?.id ?: 0) }

}