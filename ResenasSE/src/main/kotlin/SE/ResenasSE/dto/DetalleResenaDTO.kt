package SE.ResenasSE.dto

data class DetalleResenaDTO(
    val idReseña: Long,
    val comentario: String,
    val calificacion: Int,
    val usuario: Any,
    val servicio: Any
)
