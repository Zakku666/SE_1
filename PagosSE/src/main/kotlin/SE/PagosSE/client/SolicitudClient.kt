package SE.PagosSE.client

import SE.PagosSE.dto.ServicioDTO
import SE.PagosSE.dto.SolicitudDTO
import SE.PagosSE.dto.UsuarioDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "SolicitudesSE", url = "http://localhost:1005/api/solicitudes")
interface SolicitudClient {
    @GetMapping("/{id}/detalle")
    fun getSolicitudDetalle(@PathVariable id: Long): SolicitudDTO
}

@FeignClient(name = "UsuariosSE", url = "http://localhost:1001/api/usuarios")
interface UsuarioClient {
    @GetMapping("/{id}")
    fun getUsuario(@PathVariable id: Long): UsuarioDTO
}

@FeignClient(name = "ServicioSE", url = "http://localhost:1003/api/servicios")
interface ServicioClient {
    @GetMapping("/{id}")
    fun getServicio(@PathVariable id: Long): ServicioDTO
}
