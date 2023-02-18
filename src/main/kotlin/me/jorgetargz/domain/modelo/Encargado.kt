package me.jorgetargz.domain.modelo


data class Encargado(
    val id: Int = 0,
    val nombre: String = "",
    val dni: String = "",
) {
    override fun toString(): String {
        return "$nombre, $dni"
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other) || (other is Encargado && other.id == id)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}