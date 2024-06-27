package nonabili.orderserviceserver.util


import com.fasterxml.jackson.annotation.JsonInclude

data class ResponseFormat<T> (
    var state: Int,
    var message: String,
    @JsonInclude(JsonInclude.Include.NON_NULL) val data: T?
)

class ResponseFormatBuilder {
    var state: Int? = null
    var message: String? = null
    fun noData(): ResponseFormat<Any> {
        return ResponseFormat(state?: 200, message?: "success", null)
    }
    fun <T> build(data: T): ResponseFormat<T> {
        return ResponseFormat(state?: 200, message?: "success", data)
    }
}
fun ResponseFormatBuilder(builder: ResponseFormatBuilder.() -> Unit): ResponseFormatBuilder =
    ResponseFormatBuilder().apply(builder)
fun ResponseFormatBuilder(state: Int, message: String) =
    ResponseFormatBuilder {
        this.state = state
        this.message = message
    }