package nonabili.orderserviceserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients(basePackages = ["nonabili.orderserviceserver.client"])
class OrderServiceServerApplication

fun main(args: Array<String>) {
    runApplication<OrderServiceServerApplication>(*args)
}
