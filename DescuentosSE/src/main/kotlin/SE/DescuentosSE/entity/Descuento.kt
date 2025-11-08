package SE.DescuentosSE.entity


import jakarta.persistence.*

@Entity
@Table(name = "descuentos")
data class Descuento(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_descuento")
    val idDescuento: Long? = null,

    @Column(name = "nombre_descuento", nullable = false, length = 100)
    var nombreDescuento: String,

    @Column(nullable = false)
    var porcentaje: Double,

    @Column(length = 255)
    var condiciones: String? = null
)
