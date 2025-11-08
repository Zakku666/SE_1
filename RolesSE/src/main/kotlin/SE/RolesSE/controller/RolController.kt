package SE.RolesSE.controller



import SE.RolesSE.entity.Rol
import SE.RolesSE.service.RolService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import jakarta.validation.Valid

@RestController
@RequestMapping("/api/roles")
class RolController @Autowired constructor(
    private val service: RolService
) {

    @GetMapping
    fun listar(): List<Rol> = service.listar()

    @GetMapping("/{id}")
    fun detalle(@PathVariable id: Long): ResponseEntity<*> {
        val rolOpt = service.porId(id)
        return if (rolOpt.isPresent) ResponseEntity.ok(rolOpt.get())
        else ResponseEntity.notFound().build<Any>()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun crear(@Valid @RequestBody rol: Rol, result: BindingResult): ResponseEntity<*> {
        if (result.hasErrors()) {
            return validar(result)
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(rol))
    }

    @PutMapping("/{id}")
    fun editar(@Valid @RequestBody rol: Rol, result: BindingResult, @PathVariable id: Long): ResponseEntity<*> {
        if (result.hasErrors()) {
            return validar(result)
        }

        val rolOpt = service.porId(id)
        if (rolOpt.isPresent) {
            val rolDb = rolOpt.get()
            rolDb.nombreRol = rol.nombreRol
            rolDb.descripcion = rol.descripcion
            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(rolDb))
        }

        return ResponseEntity.notFound().build<Any>()
    }

    @DeleteMapping("/{id}")
    fun eliminar(@PathVariable id: Long): ResponseEntity<*> {
        val rolOpt = service.porId(id)
        return if (rolOpt.isPresent) {
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
