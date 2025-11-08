package SE.PagosSE.controller

import SE.PagosSE.client.SolicitudClient
import SE.PagosSE.dto.DetallePagoDTO
import SE.PagosSE.entity.Pago
import SE.PagosSE.service.PagoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/pagos")
class PagoController(private val service: PagoService,private val solicitudClient: SolicitudClient) {

    @GetMapping
    fun listar(): List<Pago> = service.listar()

    @GetMapping("/{id}")
    fun porId(@PathVariable id: Long): ResponseEntity<Pago> =
        service.porId(id).map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())

    @PostMapping
    fun crear(@RequestBody pago: Pago): Pago = service.guardar(pago)

    @PutMapping("/{id}")
    fun actualizar(@PathVariable id: Long, @RequestBody pago: Pago): ResponseEntity<Pago> =
        service.actualizar(id, pago)
            .map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())

    @DeleteMapping("/{id}")
    fun eliminar(@PathVariable id: Long): ResponseEntity<Void> {
        service.eliminar(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/{id}/detalle")
    fun detallePago(@PathVariable id: Long): ResponseEntity<DetallePagoDTO> {
        val pagoOpt = service.porId(id)
        return if (pagoOpt.isPresent) {
            val pago = pagoOpt.get()
            val solicitud = solicitudClient.getSolicitudDetalle(pago.idSolicitud)

            val detalle = DetallePagoDTO(
                idPago = pago.idPago!!,
                monto = pago.monto,
                fechaPago = pago.fechaPago,
                metodoPago = pago.metodoPago,
                solicitud = solicitud
            )
            ResponseEntity.ok(detalle)
        } else {
            ResponseEntity.notFound().build()
        }
    }

}
