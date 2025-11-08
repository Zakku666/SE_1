package SE.PagosSE

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class PagosSeApplication

fun main(args: Array<String>) {
	runApplication<PagosSeApplication>(*args)
}
