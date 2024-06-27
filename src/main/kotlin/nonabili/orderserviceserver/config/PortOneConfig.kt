package nonabili.orderserviceserver.config

import com.siot.IamportRestClient.IamportClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PortOneConfig(
    @Value("\${portOne.apiKey}") val apiKey: String,
    @Value("\${portOne.secretKey}") val secretKey: String
) {
    @Bean
    fun iamportClient(): IamportClient {
        return IamportClient(apiKey, secretKey)
    }
}