package SE.ServiciosSE.controller

import SE.ServiciosSE.entity.Servicio
import SE.ServiciosSE.service.ServicioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import jakarta.validation.Valid

@RestController
@RequestMapping("/api/servicios")
class ServicioController @Autowired constructor(
    private val service: ServicioService
) {

    @GetMapping
    fun listar(): List<Servicio> = service.listar()

    @GetMapping("/{id}")
    fun detalle(@PathVariable id: Long): ResponseEntity<*> {
        val servicioOpt = service.porId(id)
        return if (servicioOpt.isPresent) ResponseEntity.ok(servicioOpt.get())
        else ResponseEntity.notFound().build<Any>()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun crear(@Valid @RequestBody servicio: Servicio, result: BindingResult): ResponseEntity<*> {
        if (result.hasErrors()) {
            return validar(result)
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(servicio))
    }

    @PutMapping("/{id}")
    fun editar(@Valid @RequestBody servicio: Servicio, result: BindingResult, @PathVariable id: Long): ResponseEntity<*> {
        if (result.hasErrors()) {
            return validar(result)
        }

        val servicioOpt = service.porId(id)
        if (servicioOpt.isPresent) {
            val servicioDb = servicioOpt.get()
            servicioDb.nombreServicio = servicio.nombreServicio
            servicioDb.descripcion = servicio.descripcion
            servicioDb.precioBase = servicio.precioBase

            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(servicioDb))
        }

        return ResponseEntity.notFound().build<Any>()
    }

    @DeleteMapping("/{id}")
    fun eliminar(@PathVariable id: Long): ResponseEntity<*> {
        val servicioOpt = service.porId(id)
        return if (servicioOpt.isPresent) {
            service.eliminar(id)
            ResponseEntity.noContent().build<Any>()
        } else {
            ResponseEntity.notFound().build<Any>()
        }
    }

    private fun validar(result: BindingResult): ResponseEntity<Map<String, String>> {
        val errores = result.fieldErrors.associate { it.field to "El campo ${it.field} ${it.defaultMessage}" }
        return ResponseEntity.badRequest().body(errores)
    }
}
