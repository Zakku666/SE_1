package SE.NotificacionesSE.service

import SE.NotificacionesSE.entity.Notificacion
import SE.NotificacionesSE.repository.NotificacionRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class NotificacionService(private val repository: NotificacionRepository) {

    fun listar(): List<Notificacion> = repository.findAll()

    fun porId(id: Long): Optional<Notificacion> = repository.findById(id)

    fun guardar(notificacion: Notificacion): Notificacion = repository.save(notificacion)

    fun eliminar(id: Long) = repository.deleteById(id)
}
