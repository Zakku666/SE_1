package SE.SolicitudesSE.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "usuarios-service", url = "http://localhost:1001/api/usuarios")
interface UsuarioClient {
    @GetMapping("/{id}")
    fun getUsuario(@PathVariable id: Long): UsuarioDTO
}


data class UsuarioDTO(
    val idUsuario: Long,
    val nombre: String,
    val apellido: String,
    val correo: String
)
