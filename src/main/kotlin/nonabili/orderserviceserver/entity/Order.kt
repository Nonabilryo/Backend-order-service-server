package nonabili.orderserviceserver.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "rental_order")
data class Order(
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        val idx: UUID = UUID.randomUUID(),
        val user: UUID,
        val article: UUID,
        val state: OrderState = OrderState.READY,
        val comment: String,
        val rentalType: RentalType,
        val period: Long,  // paidAt + rentalType * period = closedAt
        val paidAt: Date? = null,
        val closedAt: Date? = null,
        val createdAt: Date = Date()
)

enum class OrderState() {
    READY, PAID, USING, CLOSE, CANCEL
}
enum class RentalType(val value: Int) {
    YEAR(0),
    MONTH(1),
    DAY(2),
    HOUR(3);

    companion object {
        private val map = values().associateBy(RentalType::value)
        fun fromInt(type: Int) = map[type]
    }
}