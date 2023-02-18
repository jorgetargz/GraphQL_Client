package me.jorgetargz.domain.modelo

data class Linea(
    val id: Int = 0,
    val numero: Int = 0,
    val tipo: String = "",
    val paradas: List<Parada> = emptyList()
) {
    override fun toString(): String {
        return "$numero, $tipo"
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other) || (other is Linea && other.id == id)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}