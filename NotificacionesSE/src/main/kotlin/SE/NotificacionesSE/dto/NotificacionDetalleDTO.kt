package SE.NotificacionesSE.dto

import java.time.LocalDateTime

data class NotificacionDetalleDTO(
    val idNotificacion: Long,
    val mensaje: String,
    val fechaEnvio: LocalDateTime,
    val estado: String,
    val usuario: Map<String, Any>?
)
