package SE.SolicitudesSE.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "servicios-service", url = "http://localhost:1003/api/servicios")
interface ServicioClient {
    @GetMapping("/{id}")
    fun getServicio(@PathVariable id: Long): ServicioDTO
}


data class ServicioDTO(
    val idServicio: Long,
    val nombreServicio: String,
    val descripcion: String,
    val precioBase: Double
)
