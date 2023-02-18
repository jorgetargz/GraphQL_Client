package me.jorgetargz.domain.modelo

data class Linea(
    val id: Int,
    val numero: Int = 0,
    val tipo: String = ""
) {
    override fun toString(): String {
        return "$numero, $tipo"
    }
}