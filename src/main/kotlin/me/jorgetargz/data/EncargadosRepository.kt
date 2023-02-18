package me.jorgetargz.data

import me.jorgetargz.domain.modelo.Encargado
import me.jorgetargz.localhost.CreateEncargadoMutation
import me.jorgetargz.localhost.GetEncargadoByParadaIdQuery
import me.jorgetargz.localhost.UpdateEncargadoMutation

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

    fun createEncargado(encargado: Encargado) = executeGraphQLMutation(
        CreateEncargadoMutation(encargado.nombre, encargado.dni)
    ) { data ->
        data.createEncargado?.let {
            Encargado(
                it.id,
                it.nombre,
                it.dni,
            )
        } ?: Encargado()
    }

    fun updateEncargado(encargado: Encargado) = executeGraphQLMutation(
        UpdateEncargadoMutation(encargado.id, encargado.nombre, encargado.dni)
    ) { data ->
        data.updateEncargado?.let {
            Encargado(
                it.id,
                it.nombre,
                it.dni,
            )
        } ?: Encargado()
    }

    fun deleteEncargado(encargado: Encargado) = executeGraphQLMutation(
        UpdateEncargadoMutation(encargado.id, encargado.nombre, encargado.dni)
    ) { data ->
        data.updateEncargado?.let {
            Encargado(
                it.id
            )
        } ?: Encargado()
    }
}