package SE.UsuariosSE.entity

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

@Entity
@Table(name = "usuarios")
data class Usuario(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idUsuario: Long? = null,

    @field:NotBlank
    @field:Size(max = 100)
    var nombre: String? = null,

    @field:NotBlank
    @field:Size(max = 100)
    var apellido: String? = null,

    @field:Email
    @field:NotBlank
    @Column(unique = true, length = 255)
    var correo: String? = null,

    @Column(length = 20)
    var telefono: String? = null,

    @Column(length = 255)
    var direccion: String? = null,

    @field:NotBlank
    @field:Size(min = 6, max = 255)
    var contrasena: String? = null
)
