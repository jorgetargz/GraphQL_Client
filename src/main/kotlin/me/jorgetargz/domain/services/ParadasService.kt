package me.jorgetargz.domain.services

import kotlinx.coroutines.flow.Flow
import me.jorgetargz.domain.modelo.Parada
import me.jorgetargz.utils.NetworkResult

interface ParadasService {

    fun getParadasByLineaId(lineaId: Int): Flow<NetworkResult<List<Parada>>>

    fun createParada(parada: Parada): Flow<NetworkResult<Parada>>

    fun updateParada(parada: Parada): Flow<NetworkResult<Parada>>

    fun deleteParada(parada: Parada): Flow<NetworkResult<Parada>>
}