package me.jorgetargz.domain.modelo


data class Parada(
    val id: Int = 0,
    val nombre: String = "",
    val direccion: String = "",
    val encargado: Encargado = Encargado(),
    val linea: Linea = Linea()
) {
    override fun toString(): String {
        return nombre
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other) || (other is Parada && other.id == id)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}