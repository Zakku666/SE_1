package SE.SolicitudesSE.service

import SE.SolicitudesSE.entity.Solicitud
import SE.SolicitudesSE.repository.SolicitudRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class SolicitudService(private val repository: SolicitudRepository) {

    fun listar(): List<Solicitud> = repository.findAll()

    fun porId(id: Long): Optional<Solicitud> = repository.findById(id)

    fun guardar(solicitud: Solicitud): Solicitud = repository.save(solicitud)

    fun eliminar(id: Long) = repository.deleteById(id)
}
