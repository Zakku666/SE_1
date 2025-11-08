package SE.RolesSE.repository


import SE.RolesSE.entity.Rol
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RolRepository : JpaRepository<Rol, Long>

