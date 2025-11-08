package SE.PagosSE.dto

import java.time.LocalDate

data class SolicitudDTO(
    val idSolicitud: Long,
    val fechaSolicitud: LocalDate,
    val estado: String,
    val cliente: UsuarioDTO,
    val servicio: ServicioDTO
)