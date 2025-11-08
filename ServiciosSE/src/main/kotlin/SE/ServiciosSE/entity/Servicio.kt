package SE.ServiciosSE.entity


import jakarta.persistence.*

@Entity
@Table(name = "servicios")
data class Servicio(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio")
    val idServicio: Long? = null,

    @Column(name = "nombre_servicio", nullable = false, length = 100)
    var nombreServicio: String,

    @Column(nullable = true, length = 255)
    var descripcion: String? = null,

    @Column(name = "precio_base", nullable = false)
    var precioBase: Double
)
