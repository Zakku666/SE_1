package SE.ServiciosSE.repository

import SE.ServiciosSE.entity.Servicio
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ServicioRepository : JpaRepository<Servicio, Long>
