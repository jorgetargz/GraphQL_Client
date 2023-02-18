package me.jorgetargz.data

import kotlinx.coroutines.flow.Flow
import me.jorgetargz.domain.modelo.Linea
import me.jorgetargz.localhost.*
import me.jorgetargz.utils.NetworkResult

class LineaRepository : BaseRepository() {

    fun getAllLineas(): Flow<NetworkResult<List<Linea>>> = executeGraphQLQuery(
        GetAllLineasQuery()
    ) { data -> data.allLineas!!.map { Linea(it!!.id, it.numero, it.tipo) } }

    fun getLinea(id: Int): Flow<NetworkResult<Linea>> = executeGraphQLQuery(
        GetLineaByIdQuery(id)
    ) { data -> Linea(data.linea!!.id, data.linea.numero, data.linea.tipo) }

    fun createLinea(tipo: String, numero: Int): Flow<NetworkResult<Linea>> = executeGraphQLMutation(
        CreateLineaMutation(tipo, numero)
    ) { data -> Linea(data.createLinea!!.id, data.createLinea.numero, data.createLinea.tipo) }

    fun updateLinea(id: Int, tipo: String, numero: Int): Flow<NetworkResult<Linea>> = executeGraphQLMutation(
        UpdateLineaMutation(id, tipo, numero)
    ) { data -> Linea(data.updateLinea!!.id, data.updateLinea.numero, data.updateLinea.tipo) }

    fun deleteLinea(id: Int): Flow<NetworkResult<Linea>> = executeGraphQLMutation(
        DeleteLineaMutation(id)
    ) { data -> Linea(data.deleteLinea!!.id) }

}