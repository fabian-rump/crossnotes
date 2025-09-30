package de.fabianrump.crossnotes.data.remote.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HolidayDto(
    @SerialName("name")
    val name: String,
    @SerialName("name_local")
    val nameLocal: String?,
    @SerialName("language")
    val language: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("country")
    val countryCode: String,
    @SerialName("location")
    val locationName: String,
    @SerialName("type")
    val type: String,
    @SerialName("date")
    val dateString: String,
    @SerialName("date_year")
    val dateYear: String,
    @SerialName("date_month")
    val dateMonth: String,
    @SerialName("date_day")
    val dateDay: String,
    @SerialName("week_day")
    val weekDay: String
)