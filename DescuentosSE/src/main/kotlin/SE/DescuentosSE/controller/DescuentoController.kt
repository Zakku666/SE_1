package SE.DescuentosSE.controller

import SE.DescuentosSE.entity.Descuento
import SE.DescuentosSE.service.DescuentoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import jakarta.validation.Valid

@RestController
@RequestMapping("/api/descuentos")
class DescuentoController @Autowired constructor(
    private val service: DescuentoService
) {

    @GetMapping
    fun listar(): List<Descuento> = service.listar()

    @GetMapping("/{id}")
    fun detalle(@PathVariable id: Long): ResponseEntity<*> {
        val descuentoOpt = service.porId(id)
        return if (descuentoOpt.isPresent) ResponseEntity.ok(descuentoOpt.get())
        else ResponseEntity.notFound().build<Any>()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun crear(@Valid @RequestBody descuento: Descuento, result: BindingResult): ResponseEntity<*> {
        if (result.hasErrors()) {
            return validar(result)
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(descuento))
    }

    @PutMapping("/{id}")
    fun editar(@Valid @RequestBody descuento: Descuento, result: BindingResult, @PathVariable id: Long): ResponseEntity<*> {
        if (result.hasErrors()) {
            return validar(result)
        }

        val descuentoOpt = service.porId(id)
        if (descuentoOpt.isPresent) {
            val descuentoDb = descuentoOpt.get()
            descuentoDb.nombreDescuento = descuento.nombreDescuento
            descuentoDb.porcentaje = descuento.porcentaje
            descuentoDb.condiciones = descuento.condiciones

            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(descuentoDb))
        }

        return ResponseEntity.notFound().build<Any>()
    }

    @DeleteMapping("/{id}")
    fun eliminar(@PathVariable id: Long): ResponseEntity<*> {
        val descuentoOpt = service.porId(id)
        return if (descuentoOpt.isPresent) {
            service.eliminar(id)
            ResponseEntity.noContent().build<Any>()
        } else {
            ResponseEntity.notFound().build<Any>()
        }
    }

    private fun validar(result: BindingResult): ResponseEntity<Map<String, String>> {
        val errores = result.fieldErrors.associate {
            it.field to "El campo ${it.field} ${it.defaultMessage}"
        }
        return ResponseEntity.badRequest().body(errores)
    }
}
