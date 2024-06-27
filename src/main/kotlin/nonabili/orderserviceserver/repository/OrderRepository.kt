package nonabili.orderserviceserver.repository

import nonabili.orderserviceserver.entity.Order
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface OrderRepository: JpaRepository<Order, UUID> {
    fun findOrderByIdx(idx: UUID): Order?
    fun findOrdersByArticle(article: UUID, pageable: Pageable): Page<Order>
}