package me.jorgetargz.domain.modelo


data class Encargado(
    val id: Int,
    val nombre: String = "",
    val dni: String = ""
) {
    override fun toString(): String {
        return "$nombre, $dni"
    }
}