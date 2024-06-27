package nonabili.orderserviceserver.util.error
class CustomError(val reason: ErrorState): RuntimeException(reason.message)