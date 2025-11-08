package SE.ResenasSE.entity

import jakarta.persistence.*

@Entity
@Table(name = "resenas")
data class Resena(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idReseña: Long? = null,

    @Column(nullable = false)
    val idUsuario: Long,

    @Column(nullable = false)
    val idServicio: Long,

    @Column(nullable = false, length = 500)
    val comentario: String,

    @Column(nullable = false)
    val calificacion: Int
)
