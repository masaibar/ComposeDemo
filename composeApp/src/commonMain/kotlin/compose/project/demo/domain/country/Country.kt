package compose.project.demo.domain.country

import kotlinx.datetime.TimeZone
import org.jetbrains.compose.resources.DrawableResource

data class Country(
    val name: String,
    val zone: TimeZone,
    val image: DrawableResource
)
