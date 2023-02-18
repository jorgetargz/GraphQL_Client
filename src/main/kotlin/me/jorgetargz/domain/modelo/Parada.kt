package me.jorgetargz.domain.modelo


data class Parada(
    val id: Int,
    val nombre: String = "",
    val direccion: String = "",
    val idEncargado: Int = 0,
    val idLinea: Int = 0
) {
    override fun toString(): String {
        return nombre
    }
}