package nonabili.orderserviceserver.controller

import jakarta.validation.Valid
import nonabili.orderserviceserver.dto.request.AcceptPostRequest
import nonabili.orderserviceserver.dto.request.OrderPostRequest
import nonabili.orderserviceserver.dto.request.PayPostRequest
import nonabili.orderserviceserver.service.OrderService
import nonabili.orderserviceserver.util.ResponseFormat
import nonabili.orderserviceserver.util.ResponseFormatBuilder
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/order")
class OrderController(val orderService: OrderService) {
    @PostMapping()
    fun postOrder(@RequestHeader requestHeaders: HttpHeaders, @Valid request: OrderPostRequest): ResponseEntity<ResponseFormat<Any>> {
        val userIdx = requestHeaders.get("userIdx")!![0]
        orderService.postOrder(userIdx, request)
        return ResponseEntity.ok(ResponseFormatBuilder { message = "success" }.noData())
    }
    @PostMapping("/pay")
    fun postPayOrder(@RequestHeader requestHeaders: HttpHeaders, @Valid request: PayPostRequest): ResponseEntity<ResponseFormat<Any>> {
        val userIdx = requestHeaders.get("userIdx")!![0]
        orderService.postPayOrder(userIdx, request)
        return ResponseEntity.ok(ResponseFormatBuilder { message = "success" }.noData())
    }
    @PostMapping("/accept")
    fun postAcceptOrder(@RequestHeader requestHeaders: HttpHeaders, @Valid acceptPostRequest: AcceptPostRequest): ResponseEntity<ResponseFormat<Any>> {
        val userIdx = requestHeaders.get("userIdx")!![0]
        orderService.postAcceptOrder(userIdx, acceptPostRequest)
        return ResponseEntity.ok(ResponseFormatBuilder { message = "success" }.noData())
    }
    @GetMapping()
    fun getOrders(
            @RequestHeader requestHeaders: HttpHeaders,
            @RequestParam(name = "articleIdx") articleIdx: String,
            @RequestParam(name = "page") page: Int
    ): ResponseEntity<ResponseFormat<Any>> {
        val userIdx = requestHeaders.get("userIdx")!![0]
        val result = orderService.getOrders(userIdx, articleIdx, page)
        return ResponseEntity.ok(ResponseFormatBuilder { message = "success"}.build(result))
    }
    @DeleteMapping
    fun deleteOrder(
            @RequestHeader requestHeaders: HttpHeaders,
            @RequestParam(name = "orderIdx") orderIdx: String
    ): ResponseEntity<ResponseFormat<Any>> {
        val userIdx = requestHeaders.get("userIdx")!![0]
        orderService.deleteOrder(userIdx, orderIdx)
        return ResponseEntity.ok(ResponseFormatBuilder { message = "success"}.noData())
    }
}