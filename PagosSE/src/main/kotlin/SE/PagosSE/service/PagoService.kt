package SE.PagosSE.service

import SE.PagosSE.entity.Pago
import SE.PagosSE.repository.PagoRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class PagoService(private val repository: PagoRepository) {

    fun listar(): List<Pago> = repository.findAll()

    fun porId(id: Long): Optional<Pago> = repository.findById(id)

    fun guardar(pago: Pago): Pago = repository.save(pago)

    fun actualizar(id: Long, pagoActualizado: Pago): Optional<Pago> {
        return repository.findById(id).map { existente ->
            val actualizado = existente.copy(
                idSolicitud = pagoActualizado.idSolicitud,
                monto = pagoActualizado.monto,
                fechaPago = pagoActualizado.fechaPago,
                metodoPago = pagoActualizado.metodoPago
            )
            repository.save(actualizado)
        }
    }

    fun eliminar(id: Long) {
        repository.deleteById(id)
    }
}
