package SE.NotificacionesSE.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "notificaciones")
data class Notificacion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idNotificacion: Long? = null,

    @Column(nullable = false)
    val idUsuario: Long,

    @Column(nullable = false, length = 255)
    val mensaje: String,

    @Column(nullable = false)
    val fechaEnvio: LocalDateTime,

    @Column(nullable = false, length = 20)
    val estado: String
)
