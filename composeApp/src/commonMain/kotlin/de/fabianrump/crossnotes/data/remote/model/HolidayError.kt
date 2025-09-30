package de.fabianrump.crossnotes.data.remote.model

sealed interface HolidayError {
    val message: String

    data class GeneralError(
        override val message: String
    ) : HolidayError

    data class NetworkError(
        val statusCode: Int?,
        override val message: String
    ) : HolidayError

    data class SerializationError(
        val error: IllegalArgumentException
    ) : HolidayError {
        override val message: String = error.message ?: error.toString()
    }
}