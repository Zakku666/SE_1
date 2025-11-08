package SE.NotificacionesSE.repository

import SE.NotificacionesSE.entity.Notificacion
import org.springframework.data.jpa.repository.JpaRepository

interface NotificacionRepository : JpaRepository<Notificacion, Long>
