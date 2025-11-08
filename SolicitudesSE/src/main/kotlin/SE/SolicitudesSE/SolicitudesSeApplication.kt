package SE.SolicitudesSE

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients

class SolicitudesSeApplication

fun main(args: Array<String>) {
	runApplication<SolicitudesSeApplication>(*args)
}
