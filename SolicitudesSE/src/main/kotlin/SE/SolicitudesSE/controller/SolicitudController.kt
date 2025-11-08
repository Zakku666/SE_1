package SE.SolicitudesSE.controller

import SE.SolicitudesSE.client.ServicioClient
import SE.SolicitudesSE.client.UsuarioClient
import SE.SolicitudesSE.dto.SolicitudDetalleDTO
import SE.SolicitudesSE.entity.Solicitud
import SE.SolicitudesSE.service.SolicitudService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/solicitudes")
class SolicitudController(private val service: SolicitudService, private val usuarioClient: UsuarioClient,
                          private val servicioClient: ServicioClient
) {

    @GetMapping
    fun listar(): List<Solicitud> = service.listar()

    @GetMapping("/{id}")
    fun detalle(@PathVariable id: Long): ResponseEntity<Solicitud> {
        val solicitudOpt = service.porId(id)
        return if (solicitudOpt.isPresent) ResponseEntity.ok(solicitudOpt.get())
        else ResponseEntity.notFound().build()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun crear(@RequestBody solicitud: Solicitud): Solicitud = service.guardar(solicitud)

    @PutMapping("/{id}")
    fun actualizar(@PathVariable id: Long, @RequestBody solicitud: Solicitud): ResponseEntity<Solicitud> {
        val solicitudOpt = service.porId(id)
        return if (solicitudOpt.isPresent) {
            val existente = solicitudOpt.get()
            existente.idUsuario = solicitud.idUsuario
            existente.idServicio = solicitud.idServicio
            existente.fechaSolicitud = solicitud.fechaSolicitud
            existente.estado = solicitud.estado
            ResponseEntity.ok(service.guardar(existente))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun eliminar(@PathVariable id: Long): ResponseEntity<Void> {
        val solicitudOpt = service.porId(id)
        return if (solicitudOpt.isPresent) {
            service.eliminar(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
    @GetMapping("/{id}/detalle")
    fun detalleCompleto(@PathVariable id: Long): ResponseEntity<SolicitudDetalleDTO> {
        val solicitudOpt = service.porId(id)
        return if (solicitudOpt.isPresent) {
            val solicitud = solicitudOpt.get()
            val usuario = usuarioClient.getUsuario(solicitud.idUsuario)
            val servicio = servicioClient.getServicio(solicitud.idServicio)

            val detalle = SolicitudDetalleDTO(
                idSolicitud = solicitud.idSolicitud!!,
                fechaSolicitud = solicitud.fechaSolicitud,
                estado = solicitud.estado,
                cliente = usuario,
                servicio = servicio
            )
            ResponseEntity.ok(detalle)
        } else {
            ResponseEntity.notFound().build()
        }
    }

}
