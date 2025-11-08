package SE.SolicitudesSE.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "solicitudes")
data class Solicitud(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud")
    var idSolicitud: Long? = null,

    @Column(name = "id_usuario", nullable = false)
    var idUsuario: Long,

    @Column(name = "id_servicio", nullable = false)
    var idServicio: Long,

    @Column(name = "fecha_solicitud", nullable = false)
    var fechaSolicitud: LocalDate,

    @Column(nullable = false)
    var estado: String
)
