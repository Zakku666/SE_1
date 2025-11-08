package SE.UsuariosSE.service


import SE.UsuariosSE.entity.Usuario
import SE.UsuariosSE.repository.UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class UsuarioService @Autowired constructor(
    private val usuarioRepository: UsuarioRepository
) {
    fun listar(): List<Usuario> = usuarioRepository.findAll()

    fun porId(id: Long): Optional<Usuario> = usuarioRepository.findById(id)

    fun guardar(usuario: Usuario): Usuario = usuarioRepository.save(usuario)

    fun eliminar(id: Long) = usuarioRepository.deleteById(id)

    fun buscarPorCorreo(correo: String): Optional<Usuario> = usuarioRepository.findByCorreo(correo)
}
