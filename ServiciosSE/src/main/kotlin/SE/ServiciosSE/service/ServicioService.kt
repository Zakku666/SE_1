package SE.ServiciosSE.service

import SE.ServiciosSE.entity.Servicio
import SE.ServiciosSE.repository.ServicioRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ServicioService(private val repository: ServicioRepository) {

    fun listar(): List<Servicio> = repository.findAll()

    fun porId(id: Long): Optional<Servicio> = repository.findById(id)

    fun guardar(servicio: Servicio): Servicio = repository.save(servicio)

    fun eliminar(id: Long) = repository.deleteById(id)
}
