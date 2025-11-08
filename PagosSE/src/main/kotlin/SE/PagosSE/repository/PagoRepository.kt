package SE.PagosSE.repository

import SE.PagosSE.entity.Pago
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PagoRepository : JpaRepository<Pago, Long>
