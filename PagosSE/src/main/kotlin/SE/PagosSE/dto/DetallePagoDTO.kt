package SE.PagosSE.dto

import java.time.LocalDate

data class DetallePagoDTO(
    val idPago: Long,
    val monto: Double,
    val fechaPago: LocalDate,
    val metodoPago: String,
    val solicitud: SolicitudDTO
)