package SE.ResenasSE.service


import SE.ResenasSE.entity.Resena
import SE.ResenasSE.repository.ResenaRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ResenaService(private val repository: ResenaRepository) {

    fun listar(): List<Resena> = repository.findAll()

    fun porId(id: Long): Optional<Resena> = repository.findById(id)

    fun guardar(reseña: Resena): Resena = repository.save(reseña)

    fun eliminar(id: Long) = repository.deleteById(id)
}
