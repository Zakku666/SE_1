package SE.RolesSE.service



import SE.RolesSE.entity.Rol
import SE.RolesSE.repository.RolRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class RolService @Autowired constructor(
    private val repository: RolRepository
) {
    fun listar(): List<Rol> = repository.findAll()

    fun porId(id: Long): Optional<Rol> = repository.findById(id)

    fun guardar(rol: Rol): Rol = repository.save(rol)

    fun eliminar(id: Long) = repository.deleteById(id)
}
