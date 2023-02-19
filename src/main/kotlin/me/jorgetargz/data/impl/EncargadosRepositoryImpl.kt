package me.jorgetargz.data.impl

import me.jorgetargz.data.EncargadosRepository
import me.jorgetargz.domain.modelo.Encargado
import me.jorgetargz.localhost.CreateEncargadoMutation
import me.jorgetargz.localhost.GetEncargadoByParadaIdQuery
import me.jorgetargz.localhost.UpdateEncargadoMutation


class EncargadosRepositoryImpl : BaseRepository(), EncargadosRepository {

    override fun getEncargadoByParadaId(paradaId: Int) = executeGraphQLQuery(
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

    override fun createEncargado(encargado: Encargado) = executeGraphQLMutation(
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

    override fun updateEncargado(encargado: Encargado) = executeGraphQLMutation(
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

    override fun deleteEncargado(encargado: Encargado) = executeGraphQLMutation(
        UpdateEncargadoMutation(encargado.id, encargado.nombre, encargado.dni)
    ) { data ->
        data.updateEncargado?.let {
            Encargado(
                it.id
            )
        } ?: Encargado()
    }
}