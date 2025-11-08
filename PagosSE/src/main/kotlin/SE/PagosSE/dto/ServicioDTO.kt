package SE.PagosSE.dto

data class ServicioDTO(
    val idServicio: Long,
    val nombreServicio: String,
    val descripcion: String,
    val precioBase: Double
)