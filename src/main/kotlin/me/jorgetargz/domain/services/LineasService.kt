package me.jorgetargz.domain.services

import kotlinx.coroutines.flow.Flow
import me.jorgetargz.domain.modelo.Linea
import me.jorgetargz.utils.NetworkResult

interface LineasService {

    fun getAllLineas(): Flow<NetworkResult<List<Linea>>>

    fun createLinea(linea: Linea): Flow<NetworkResult<Linea>>

    fun updateLinea(linea: Linea): Flow<NetworkResult<Linea>>

    fun deleteLinea(linea: Linea): Flow<NetworkResult<Linea>>

}