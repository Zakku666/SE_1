package SE.RolesSE.entity;


import jakarta.persistence.*
import lombok.Data

@Entity
@Table(name = "roles")
@Data
class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    var idRol: Long? = null

    @Column(name = "nombre_rol", nullable = false, unique = true, length = 100)
    var nombreRol: String? = null

    @Column(name = "descripcion", length = 255)
    var descripcion: String? = null
}
