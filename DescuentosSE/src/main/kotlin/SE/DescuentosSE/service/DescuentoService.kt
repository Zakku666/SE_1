package SE.DescuentosSE.service

import SE.DescuentosSE.entity.Descuento
import SE.DescuentosSE.repository.DescuentoRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class DescuentoService(private val repository: DescuentoRepository) {

    fun listar(): List<Descuento> = repository.findAll()

    fun porId(id: Long): Optional<Descuento> = repository.findById(id)

    fun guardar(descuento: Descuento): Descuento = repository.save(descuento)

    fun eliminar(id: Long) = repository.deleteById(id)
}
