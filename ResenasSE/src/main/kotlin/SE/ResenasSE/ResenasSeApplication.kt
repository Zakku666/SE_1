package SE.ResenasSE

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class ResenasSeApplication

fun main(args: Array<String>) {
	runApplication<ResenasSeApplication>(*args)
}
