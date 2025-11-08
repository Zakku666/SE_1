package SE.UsuariosSE.controller

import SE.UsuariosSE.entity.Usuario
import SE.UsuariosSE.service.UsuarioService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/usuarios")
class UsuarioController @Autowired constructor(
    private val service: UsuarioService,
    private val passwordEncoder: BCryptPasswordEncoder
) {

    @GetMapping
    fun listar(): List<Usuario> = service.listar()

    @GetMapping("/{id}")
    fun detalle(@PathVariable id: Long): ResponseEntity<*> {
        val usuarioOpt = service.porId(id)
        return if (usuarioOpt.isPresent) ResponseEntity.ok(usuarioOpt.get())
        else ResponseEntity.notFound().build<Any>()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun crear(@Valid @RequestBody usuario: Usuario, result: BindingResult): ResponseEntity<*> {
        if (service.buscarPorCorreo(usuario.correo!!).isPresent) {
            return ResponseEntity.badRequest()
                .body(mapOf("mensaje" to "Ya existe un usuario con ese correo"))
        }

        if (result.hasErrors()) {
            return validar(result)
        }

        usuario.contrasena = passwordEncoder.encode(usuario.contrasena)
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(usuario))
    }

    @PutMapping("/{id}")
    fun editar(@Valid @RequestBody usuario: Usuario, result: BindingResult, @PathVariable id: Long): ResponseEntity<*> {
        if (result.hasErrors()) {
            return validar(result)
        }

        val usuarioOpt = service.porId(id)
        if (usuarioOpt.isPresent) {
            val usuarioDb = usuarioOpt.get()
            usuarioDb.nombre = usuario.nombre
            usuarioDb.apellido = usuario.apellido
            usuarioDb.correo = usuario.correo
            usuarioDb.telefono = usuario.telefono
            usuarioDb.direccion = usuario.direccion

            if (!usuario.contrasena.isNullOrBlank()) {
                usuarioDb.contrasena = passwordEncoder.encode(usuario.contrasena)
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(usuarioDb))
        }

        return ResponseEntity.notFound().build<Any>()
    }

    @DeleteMapping("/{id}")
    fun eliminar(@PathVariable id: Long): ResponseEntity<*> {
        val usuarioOpt = service.porId(id)
        return if (usuarioOpt.isPresent) {
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

    @GetMapping("/ping")
    fun ping(): String {
        return "Usuarios service OK"
    }
    @PostMapping("/login")
    fun login(@RequestBody credenciales: Map<String, String>): ResponseEntity<*> {
        val correo = credenciales["correo"]
        val contrasena = credenciales["contrasena"]

        if (correo.isNullOrBlank() || contrasena.isNullOrBlank()) {
            return ResponseEntity.badRequest().body(mapOf("mensaje" to "Correo y contraseña son obligatorios"))
        }

        val usuarioOpt = service.buscarPorCorreo(correo)
        if (usuarioOpt.isEmpty) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(mapOf("mensaje" to "Usuario no encontrado"))
        }

        val usuario = usuarioOpt.get()
        return if (passwordEncoder.matches(contrasena, usuario.contrasena)) {
            ResponseEntity.ok(usuario)
        } else {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(mapOf("mensaje" to "Contraseña incorrecta"))
        }
    }


}
