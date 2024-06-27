package nonabili.orderserviceserver.util.error

import org.springframework.http.HttpStatus

enum class ErrorState(val status: HttpStatus = HttpStatus.OK, val message: String) {

    ERROR_FORMAT(HttpStatus.BAD_REQUEST, "It's test"),
    CANT_SAVE(HttpStatus.INTERNAL_SERVER_ERROR, "I don't know this error, maybe "),
    SERVER_UNAVAILABLE(HttpStatus.INTERNAL_SERVER_ERROR, "Server is unavailable"),
    DIFFERENT_USER(HttpStatus.FORBIDDEN, "User is not ower"),
    UNAUTHENTICATED_PAYMENT(HttpStatus.BAD_REQUEST, "Payment is wrong"),

    EMPTY_REQUEST(HttpStatus.BAD_REQUEST, "Request is empty"),

    ID_IS_ALREADY_USED(HttpStatus.BAD_REQUEST, "Id is already used"),
    NAME_IS_ALREADY_USED(HttpStatus.BAD_REQUEST, "Name is already used"),
    TELL_IS_ALREADY_USED(HttpStatus.BAD_REQUEST, "Tell is already used"),
    EMAIL_IS_ALREADY_USED(HttpStatus.BAD_REQUEST, "Email is already used"),

    NOT_FOUND_ID(HttpStatus.NOT_FOUND, "Not found Id"),
    NOT_FOUND_IDX(HttpStatus.NOT_FOUND, "Not found Idx"),
    NOT_FOUND_USERNAME(HttpStatus.NOT_FOUND, "Not found user"),
    NOT_FOUND_STATUS(HttpStatus.NOT_FOUND, "Not found status"),
    NOT_FOUDN_CHAT(HttpStatus.NOT_FOUND, "Not found chat"),
    NOT_FOUND_ORDER(HttpStatus.NOT_FOUND, "Not found order"),

    WRONG_PASSWORD(HttpStatus.BAD_REQUEST, "Wrong password"),

    NOT_VERIFED_EMAIL(HttpStatus.BAD_REQUEST, "Email not verifed"),
    NOT_VERIFED_TELL(HttpStatus.BAD_REQUEST, "Tell not verifed"),
}