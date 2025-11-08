package SE.ResenasSE.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "UsuarioSE", url = "http://localhost:1001/api/usuarios")
interface UsuarioClient {
    @GetMapping("/{id}")
    fun getUsuario(@PathVariable id: Long): Any
}

@FeignClient(name = "ServiciosSE", url = "http://localhost:1003/api/servicios")
interface ServicioClient {
    @GetMapping("/{id}")
    fun getServicio(@PathVariable id: Long): Any
}
