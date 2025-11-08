package SE.ResenasSE.repository

import SE.ResenasSE.entity.Resena
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ResenaRepository : JpaRepository<Resena, Long>
