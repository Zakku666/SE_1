package SE.ResenasSE.controller


import SE.ResenasSE.client.ServicioClient
import SE.ResenasSE.client.UsuarioClient
import SE.ResenasSE.dto.DetalleResenaDTO
import SE.ResenasSE.entity.Resena
import SE.ResenasSE.service.ResenaService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/reseñas")
class ReseñaController(
    private val service: ResenaService,
    private val usuarioClient: UsuarioClient,
    private val servicioClient: ServicioClient
) {

    @GetMapping
    fun listar(): List<Resena> = service.listar()

    @GetMapping("/{id}")
    fun porId(@PathVariable id: Long): ResponseEntity<Resena> {
        val reseñaOpt = service.porId(id)
        return if (reseñaOpt.isPresent) ResponseEntity.ok(reseñaOpt.get())
        else ResponseEntity.notFound().build()
    }

    @PostMapping
    fun crear(@RequestBody reseña: Resena): ResponseEntity<Resena> {
        val nuevaReseña = service.guardar(reseña)
        return ResponseEntity.ok(nuevaReseña)
    }

    @PutMapping("/{id}")
    fun actualizar(@PathVariable id: Long, @RequestBody reseña: Resena): ResponseEntity<Resena> {
        val existente = service.porId(id)
        return if (existente.isPresent) {
            val actualizada = existente.get().copy(
                idUsuario = reseña.idUsuario,
                idServicio = reseña.idServicio,
                comentario = reseña.comentario,
                calificacion = reseña.calificacion
            )
            ResponseEntity.ok(service.guardar(actualizada))
        } else ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{id}")
    fun eliminar(@PathVariable id: Long): ResponseEntity<Void> {
        val existe = service.porId(id)
        return if (existe.isPresent) {
            service.eliminar(id)
            ResponseEntity.noContent().build()
        } else ResponseEntity.notFound().build()
    }

    @GetMapping("/{id}/detalle")
    fun detalle(@PathVariable id: Long): ResponseEntity<DetalleResenaDTO> {
        val reseñaOpt = service.porId(id)
        return if (reseñaOpt.isPresent) {
            val reseña = reseñaOpt.get()
            val usuario = usuarioClient.getUsuario(reseña.idUsuario)
            val servicio = servicioClient.getServicio(reseña.idServicio)

            val detalle = DetalleResenaDTO(
                idReseña = reseña.idReseña!!,
                comentario = reseña.comentario,
                calificacion = reseña.calificacion,
                usuario = usuario,
                servicio = servicio
            )
            ResponseEntity.ok(detalle)
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
