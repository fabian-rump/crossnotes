package de.fabianrump.crossnotes.data.remote.api

import arrow.core.raise.Raise
import arrow.core.raise.catch
import arrow.core.raise.ensure
import de.fabianrump.crossnotes.data.remote.model.HolidayError.GeneralError
import de.fabianrump.crossnotes.data.remote.model.HolidayError.NetworkError
import de.fabianrump.crossnotes.data.remote.dtos.HolidayDto
import de.fabianrump.crossnotes.data.remote.model.HolidayError
import de.fabianrump.crossnotes.data.remote.provider.HOLIDAY_API_KEY
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.URLProtocol
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal class HolidayApi : AutoCloseable {
    private val superHeroesApi = HttpClient {
        defaultRequest {
            host = "holidays.abstractapi.com"

            url {
                protocol = URLProtocol.HTTPS

                parameters.append(name = "api_key", value = HOLIDAY_API_KEY)
                parameters.append(name = "country", value = "DE")
            }
        }

        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }
        install(HttpRequestRetry) {
            retryOnServerErrors(maxRetries = 1)
            exponentialDelay()
        }
    }

    override fun close() {
        superHeroesApi.close()
    }

    suspend fun Raise<HolidayError>.fetchHolidays(year: Int, month: Int, day: Int): List<HolidayDto> {
        val response = catch(
            block = {
                Napier.d { "Fetching holidays..." }
                superHeroesApi.get(urlString = "v1") {
                    url {
                        parameters.append(name = "year", value = "$year")
                        parameters.append(name = "month", value = "$month")
                        parameters.append(name = "day", value = "$day")
                    }
                }
            },
            catch = {
                Napier.d { "Failed to fetch holidays: $it" }
                raise(
                    r = GeneralError(
                        message = it.message.orEmpty()
                    )
                )
            }
        )

        ensure(condition = response.status.isSuccess()) {
            raise(
                r = NetworkError(
                    statusCode = response.status.value,
                    message = response.bodyAsText()
                )
            )
        }

        return catch<IllegalArgumentException, _>(block = { response.body<List<HolidayDto>>() }) {
            raise(r = HolidayError.SerializationError(error = it))
        }
    }
}