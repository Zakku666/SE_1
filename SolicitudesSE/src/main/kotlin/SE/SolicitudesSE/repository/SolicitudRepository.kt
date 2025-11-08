package SE.SolicitudesSE.repository

import SE.SolicitudesSE.entity.Solicitud
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SolicitudRepository : JpaRepository<Solicitud, Long>
