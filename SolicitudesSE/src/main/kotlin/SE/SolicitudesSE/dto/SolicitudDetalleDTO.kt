package SE.SolicitudesSE.dto

import SE.SolicitudesSE.client.ServicioDTO
import SE.SolicitudesSE.client.UsuarioDTO
import java.time.LocalDate

data class SolicitudDetalleDTO(
    val idSolicitud: Long,
    val fechaSolicitud: LocalDate,
    val estado: String,
    val cliente: UsuarioDTO,
    val servicio: ServicioDTO
)



