package SE.PagosSE.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "pagos")
data class Pago(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idPago: Long? = null,

    @Column(nullable = false)
    val idSolicitud: Long,

    @Column(nullable = false)
    val monto: Double,

    @Column(nullable = false)
    val fechaPago: LocalDate,

    @Column(nullable = false, length = 50)
    val metodoPago: String
)
