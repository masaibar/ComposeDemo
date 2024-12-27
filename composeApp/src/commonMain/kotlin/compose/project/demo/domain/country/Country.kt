package compose.project.demo.domain.country

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.DrawableResource

data class Country(
    val name: String,
    val zone: TimeZone,
    val image: DrawableResource
) {
    fun currentTimeAt(): String {
        val time = Clock.System.now()
        val localTime = time.toLocalDateTime(this.zone).time
        return "The time in ${this.name} is ${localTime.formatted()}"
    }

    private fun LocalTime.formatted() = "$hour:$minute:$second"
}
