package SE.NotificacionesSE.controller

import SE.NotificacionesSE.client.UsuarioClient
import SE.NotificacionesSE.dto.NotificacionDetalleDTO
import SE.NotificacionesSE.entity.Notificacion
import SE.NotificacionesSE.service.NotificacionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/notificaciones")
class NotificacionController(
    private val service: NotificacionService,
    private val usuarioClient: UsuarioClient
) {

    @GetMapping
    fun listar(): List<Notificacion> = service.listar()

    @GetMapping("/{id}")
    fun porId(@PathVariable id: Long): ResponseEntity<Notificacion> {
        val notificacion = service.porId(id)
        return if (notificacion.isPresent) ResponseEntity.ok(notificacion.get())
        else ResponseEntity.notFound().build()
    }

    @PostMapping
    fun crear(@RequestBody notificacion: Notificacion): ResponseEntity<Notificacion> {
        val nueva = service.guardar(
            notificacion.copy(
                fechaEnvio = LocalDateTime.now()
            )
        )
        return ResponseEntity.ok(nueva)
    }

    @PutMapping("/{id}")
    fun actualizar(@PathVariable id: Long, @RequestBody datos: Notificacion): ResponseEntity<Notificacion> {
        val notificacionOpt = service.porId(id)
        return if (notificacionOpt.isPresent) {
            val notificacion = notificacionOpt.get().copy(
                idUsuario = datos.idUsuario,
                mensaje = datos.mensaje,
                fechaEnvio = datos.fechaEnvio,
                estado = datos.estado
            )
            ResponseEntity.ok(service.guardar(notificacion))
        } else ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{id}")
    fun eliminar(@PathVariable id: Long): ResponseEntity<Void> {
        val notificacionOpt = service.porId(id)
        return if (notificacionOpt.isPresent) {
            service.eliminar(id)
            ResponseEntity.noContent().build()
        } else ResponseEntity.notFound().build()
    }

    @GetMapping("/{id}/detalle")
    fun detalle(@PathVariable id: Long): ResponseEntity<NotificacionDetalleDTO> {
        val notificacionOpt = service.porId(id)
        return if (notificacionOpt.isPresent) {
            val notificacion = notificacionOpt.get()
            val usuario = try {
                usuarioClient.getUsuario(notificacion.idUsuario)
            } catch (e: Exception) {
                null
            }

            val detalle = NotificacionDetalleDTO(
                idNotificacion = notificacion.idNotificacion!!,
                mensaje = notificacion.mensaje,
                fechaEnvio = notificacion.fechaEnvio,
                estado = notificacion.estado,
                usuario = usuario
            )
            ResponseEntity.ok(detalle)
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
