package nonabili.orderserviceserver.dto.request

import org.hibernate.validator.constraints.UUID

data class OrderPostRequest(
    @UUID
    val article: String,
    val comment: String,
    val rentalType: Int,
    val period: Long,
)