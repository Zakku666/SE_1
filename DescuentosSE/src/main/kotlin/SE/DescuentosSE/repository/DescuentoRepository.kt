package SE.DescuentosSE.repository


import SE.DescuentosSE.entity.Descuento
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DescuentoRepository : JpaRepository<Descuento, Long>
