package SE.NotificacionesSE

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class NotificacionesSeApplication

fun main(args: Array<String>) {
	runApplication<NotificacionesSeApplication>(*args)
}
