package SE.UsuariosSE.repository

import SE.UsuariosSE.entity.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UsuarioRepository : JpaRepository<Usuario, Long> {
    fun findByCorreo(correo: String): Optional<Usuario>
}

